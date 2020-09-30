CREATE TABLE [active_queue] (
  [id] bigint NOT NULL,
  [member_id] bigint NOT NULL,
  [assignment_type] varchar(100) NOT NULL,
  [customer_id] bigint NOT NULL,
  [program_id] bigint NOT NULL,
  [program_task_id] bigint NOT NULL,
  [assigned_to] varchar(100) NOT NULL,
  [start_time] datetime2 NOT NULL,
  [end_time] datetime2 NULL,
  [is_closed] bit NOT NULL,
  [remarks] varchar(250) NULL,
  [created_by] varchar(100) NOT NULL,
  [created_at] datetime2 NOT NULL,
  [updated_by] varchar(100) NULL,
  [updated_at] datetime2 NULL,
  CONSTRAINT [pk_active_queues] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
GO