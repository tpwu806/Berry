ALTER TABLE org_org 
ADD COLUMN ISDELETED tinyint(4) DEFAULT NULL,
ADD COLUMN DELETED_BY int(10) unsigned DEFAULT NULL;
ALTER TABLE org_org
ADD CONSTRAINT FK_ORG_OODELETEDBY FOREIGN KEY(DELETED_BY) REFERENCES user_user(ID);