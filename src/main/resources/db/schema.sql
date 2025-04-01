CREATE TABLE IF NOT EXISTS T_BOOKMARK_SEQ(
    bk_seq_id INT NOT NULL ,
    PRIMARY KEY (bk_seq_id)
);
CREATE TABLE IF NOT EXISTS T_BOOKMARK(
    id CHAR(18) PRIMARY KEY,
    type_id CHAR(19) NOT NULL ,
    tenant_id VARCHAR(255) NOT NULL,
    comment TEXT NOT NULL,
    url TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS T_BOOKMARK_TYPE(
    type_id CHAR(19) PRIMARY KEY,
    type_name CHAR(19) NOT NULL,
    tenant_id VARCHAR(255) NOT NULL,
    -- 联合唯一约束（确保业务唯一性）
    UNIQUE (tenant_id, type_name)
);