CREATE TABLE "DBA"."100_AUTHORS" (
	"author_id" NUMERIC(9,0) NOT NULL DEFAULT AUTOINCREMENT,
	"author_name" VARCHAR(255) NULL,
	PRIMARY KEY ( "author_id" ASC )
);

CREATE TABLE "DBA"."101_PUBLISHERS" (
	"publisher_id" NUMERIC(9,0) NOT NULL DEFAULT AUTOINCREMENT,
	"publisher_name" VARCHAR(255) NULL,
	PRIMARY KEY ( "publisher_id" ASC )
);

CREATE TABLE "DBA"."102_BOOKS" (
	"isbn" NUMERIC(9,0) NOT NULL DEFAULT AUTOINCREMENT,
	"title" VARCHAR(255) NULL,
	"no_of_pages" NUMERIC(9,0) NULL,
	"year_published" NUMERIC(4,0) NULL,
	"publisher_id" NUMERIC(9,0) NULL,
	PRIMARY KEY ( "isbn" ASC )
);

ALTER TABLE "DBA"."102_BOOKS" ADD CONSTRAINT "FK_102_101_PUBLISHERS" FOREIGN KEY ( "publisher_id" ASC ) REFERENCES "DBA"."101_PUBLISHERS" ( "publisher_id" );

CREATE TABLE "DBA"."103_BOOKS_AUTHORS" (
	"isbn" NUMERIC(9,0) NOT NULL,
	"author_id" NUMERIC(9,0) NOT NULL,
	PRIMARY KEY ( "isbn" ASC, "author_id" ASC )
);

ALTER TABLE "DBA"."103_BOOKS_AUTHORS" ADD CONSTRAINT "FK_103_100" NOT NULL FOREIGN KEY ( "author_id" ASC ) REFERENCES "DBA"."100_AUTHORS" ( "author_id" );
ALTER TABLE "DBA"."103_BOOKS_AUTHORS" ADD CONSTRAINT "FK_103_102" NOT NULL FOREIGN KEY ( "isbn" ASC ) REFERENCES "DBA"."102_BOOKS" ( "isbn" );

CREATE TABLE "DBA"."104_BOOKS_COPIES" (
	"copy_id" NUMERIC(9,0) NOT NULL DEFAULT AUTOINCREMENT,
	"isbn" NUMERIC(9,0) NOT NULL,
	PRIMARY KEY ( "copy_id" ASC )
);

ALTER TABLE "DBA"."104_BOOKS_COPIES" ADD CONSTRAINT "FK_104_102" NOT NULL FOREIGN KEY ( "isbn" ASC ) REFERENCES "DBA"."102_BOOKS" ( "isbn" );













