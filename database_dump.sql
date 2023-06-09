-- H2 2.1.214; 
;              
CREATE USER IF NOT EXISTS "SA" SALT '07b16c25f5d33c8f' HASH 'b3dedd08588c4d7f63b67401299b62f03ea4bc5456ea3685318b7664bf11d42b' ADMIN;          
CREATE MEMORY TABLE "PUBLIC"."ADDRESS"(
    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "CITY" CHARACTER VARYING(255),
    "LATITUDE" FLOAT(53),
    "LONGITUDE" FLOAT(53),
    "STATE" CHARACTER VARYING(255),
    "STREET" CHARACTER VARYING(255),
    "ZIP" CHARACTER VARYING(255)
);          
ALTER TABLE "PUBLIC"."ADDRESS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ADDRESS;  
CREATE MEMORY TABLE "PUBLIC"."IMAGES"(
    "PROPERTY_ID" BIGINT NOT NULL,
    "IMAGE" CHARACTER VARYING(255)
);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.IMAGES;   
CREATE MEMORY TABLE "PUBLIC"."LOGGER_ENTRY"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "DETAILS" CHARACTER VARYING(255),
    "EXCEPTION_MESSAGE" CHARACTER VARYING(255),
    "USER_ID" BIGINT
);             
ALTER TABLE "PUBLIC"."LOGGER_ENTRY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_B" PRIMARY KEY("ID");  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.LOGGER_ENTRY;             
CREATE MEMORY TABLE "PUBLIC"."MESSAGE"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "DATE" DATE,
    "MESSAGE" CHARACTER VARYING(255),
    "TIME" TIME,
    "PROPERTY_ID" BIGINT,
    "RECIPIENT_ID" BIGINT NOT NULL,
    "SENDER_ID" BIGINT NOT NULL
);       
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;  
CREATE MEMORY TABLE "PUBLIC"."OFFER"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "DETAILS" CHARACTER VARYING,
    "OFFER_AMOUNT" FLOAT(53),
    "STATUS" CHARACTER VARYING(255),
    "PROPERTY_ID" BIGINT,
    "USER_ID" BIGINT
);            
ALTER TABLE "PUBLIC"."OFFER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.OFFER;    
CREATE MEMORY TABLE "PUBLIC"."PROPERTY"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "AREA" FLOAT(53),
    "DESCRIPTION" CHARACTER VARYING,
    "NO_OF_BATHROOMS" FLOAT(53),
    "NO_OF_BEDROOMS" INTEGER,
    "PLOT_SIZE" FLOAT(53),
    "PRICE" FLOAT(53),
    "STATUS" CHARACTER VARYING(255),
    "TITLE" CHARACTER VARYING(255),
    "ADDRESS_ID" INTEGER,
    "USER_ID" BIGINT
);        
ALTER TABLE "PUBLIC"."PROPERTY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PROPERTY; 
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 6) NOT NULL,
    "CREATED_ON" TIMESTAMP(6),
    "MODIFIED_ON" TIMESTAMP(6),
    "APPROVED" BOOLEAN NOT NULL,
    "EMAIL" CHARACTER VARYING(255) NOT NULL,
    "NAME" CHARACTER VARYING(255) NOT NULL,
    "PASSWORD" CHARACTER VARYING(255),
    "ROLE" CHARACTER VARYING(255),
    "ADDRESS_ID" INTEGER
);   
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("ID");        
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.USERS;    
INSERT INTO "PUBLIC"."USERS" VALUES
(1, NULL, NULL, TRUE, 'admin@gmail.com', 'admin', '$2a$10$7VRQOgdWJ.p9Kcl96ftWu.439tyTvTFpBhG7lnV4jcaKpjW3FfLj.', 'ADMIN', NULL),
(2, NULL, NULL, TRUE, 'owner@gmail.com', 'owner', '$2a$10$xtyI11MB7xh8pL7pngtDqehFCuQ4FoI3wQ0Z1q77QuVHGlNsNOFnO', 'OWNER', NULL),
(3, NULL, NULL, FALSE, 'owner1@gmail.com', 'owner1', '$2a$10$NmHEuDJIvMfeUXTdEYoKWO5xX6xsof3AcuzA4FCCRD0SW2EBG/O4q', 'OWNER', NULL),
(4, NULL, NULL, TRUE, 'user1@gmail.com', 'user1', '$2a$10$HIvrzkM2fCWqokvZNH/A1uSGhqiPJPxdOtHVK7/YWHPOPLuW2Bj4q', 'CUSTOMER', NULL),
(5, NULL, NULL, TRUE, 'user2@gmail.com', 'user2', '$2a$10$Fux5rhMm7G46qouxCcurluSW1nnBVXkU/K9MBCe9aV2aorCk8joo2', 'CUSTOMER', NULL);         
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."UK_6DOTKOTT2KJSP8VW4D0M25FB7" UNIQUE("EMAIL");           
ALTER TABLE "PUBLIC"."PROPERTY" ADD CONSTRAINT "PUBLIC"."FKGCDUYFIUNK1EWG7920PW4L3O9" FOREIGN KEY("ADDRESS_ID") REFERENCES "PUBLIC"."ADDRESS"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKBI5AVHE69AOL2MB1LNM6R4O2P" FOREIGN KEY("SENDER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;       
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."FKDITU6LR4EK16TKXTDSNE0GXIB" FOREIGN KEY("ADDRESS_ID") REFERENCES "PUBLIC"."ADDRESS"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKE0JLS8QFY50GVTWEE3EGCYPV" FOREIGN KEY("PROPERTY_ID") REFERENCES "PUBLIC"."PROPERTY"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."OFFER" ADD CONSTRAINT "PUBLIC"."FKAA26RDSUW275QBRCJQHRLHYT8" FOREIGN KEY("PROPERTY_ID") REFERENCES "PUBLIC"."PROPERTY"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKC60HIB4MR1PL61LMCPVEHY3M5" FOREIGN KEY("RECIPIENT_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."OFFER" ADD CONSTRAINT "PUBLIC"."FKAYDJFXFT8XL9EITCDY1GD7BK8" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;           
ALTER TABLE "PUBLIC"."IMAGES" ADD CONSTRAINT "PUBLIC"."FKFCHYAEA44UR8SD0FWJVWGFD4N" FOREIGN KEY("PROPERTY_ID") REFERENCES "PUBLIC"."PROPERTY"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."PROPERTY" ADD CONSTRAINT "PUBLIC"."FKO76RPD66L7RDHEHEO63PMPMY5" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;        
