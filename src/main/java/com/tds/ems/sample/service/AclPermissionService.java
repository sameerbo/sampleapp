package com.tds.ems.sample.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.tds.ems.sample.entities.Customer;
import com.tds.ems.sample.entities.CustomerPrograms;

@Service
@Transactional
public class AclPermissionService {
	 @Autowired
	 private MutableAclService aclService;
	 
	 @Autowired
	 private PlatformTransactionManager transactionManager;
	 
	 public void addPermissionForUser(Customer targetObj, Permission permission, String username) {
	        final Sid sid = new PrincipalSid(username);
	        addPermissionForSid(targetObj, permission, sid);
	    }
	 
	public void addPermissionForAuthority(Customer targetObj, Permission permission, String authority) {
	        final Sid sid = new GrantedAuthoritySid(authority);
	        addPermissionForSid(targetObj, permission, sid);
	}

	private void addPermissionForSid(Customer targetObj, Permission permission, Sid sid) {
		final TransactionTemplate tt = new TransactionTemplate(transactionManager);

        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                final ObjectIdentity oi = new ObjectIdentityImpl(targetObj.getClass(), targetObj.getId());
                
                MutableAcl acl = null;
                try {
                    acl = (MutableAcl) aclService.readAclById(oi);
                } catch (final NotFoundException nfe) {
                    acl = aclService.createAcl(oi);
                }
                acl.insertAce(acl.getEntries().size(), permission, sid, true);
                aclService.updateAcl(acl);
            }
        });
    }

	public void inheritPermission(Customer customer, CustomerPrograms customerProgram) {
		ObjectIdentity objectIdentity = new ObjectIdentityImpl(customerProgram.getClass(), customerProgram.getId());
		 MutableAcl acl = null;
		    try {
		        acl = (MutableAcl) aclService.readAclById(objectIdentity);
		    } catch (NotFoundException e) {
		    	acl = aclService.createAcl(objectIdentity);
		    }
		    ObjectIdentity parentObjectIdentity = new ObjectIdentityImpl(customer.getClass(), customer.getId());
		    MutableAcl parentAcl = null;
		    try {
		        parentAcl = (MutableAcl) aclService.readAclById(parentObjectIdentity);
		    } catch (NotFoundException e) {
		        parentAcl = aclService.createAcl(parentObjectIdentity);
		    }
		    acl.setEntriesInheriting(true);
		    acl.setParent(parentAcl);
		    aclService.updateAcl(acl);
	}
}

