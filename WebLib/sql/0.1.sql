CREATE TABLE "DBA"."100_AUTHORS" (
	"author_id" NUMERIC(9,0) NOT NULL DEFAULT AUTOINCREMENT,
	"author_name" VARCHAR(255) NULL,
	PRIMARY KEY ( "author_id" ASC )
) IN "system";
