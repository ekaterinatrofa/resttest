CREATE TABLE "category" (
  "id" INT,
  "name" VARCHAR,
  "created_by" INT,
  "created_at" TIMESTAMP DEFAULT (now()),
  PRIMARY KEY ("id"),

  CONSTRAINT "created_by"
     FOREIGN KEY ("created_by")
         REFERENCES "jaz_user" ("id")
);

CREATE TABLE "sub_category" (
  "id" SERIAL,
  "category_id" INT,
  "name" VARCHAR,
  "created_at" TIMESTAMP DEFAULT (now()),
  PRIMARY KEY(id),

  CONSTRAINT "category_id"
     FOREIGN KEY ("category_id")
         REFERENCES "category" ("id")
);

CREATE TABLE "auction" (
  "id" INT UNIQUE,
  "title" VARCHAR,
  "description" VARCHAR,
  "sub_category_id" INT,
  "price" INT,
  "created_by" INT,
  "created_at" TIMESTAMP DEFAULT (now()),
  PRIMARY KEY(id),

  CONSTRAINT "created_by"
     FOREIGN KEY ("created_by")
         REFERENCES "jaz_user" ("id"),

  CONSTRAINT "sub_category_id"
     FOREIGN KEY ("sub_category_id")
         REFERENCES "sub_category" ("id")
);

--parameter table def
CREATE TABLE "parameter" (
  "id" BIGINT,
  "key" VARCHAR,
  "created_at" TIMESTAMP DEFAULT (now()),
  PRIMARY KEY(id)
);

--linking table
CREATE TABLE "auction_parameter" (
  "auction_id" BIGINT,
  "parameter_id" BIGINT,
  "value" VARCHAR,
  "created_at" TIMESTAMP DEFAULT (now()),

  CONSTRAINT "parameter_id"
     FOREIGN KEY ("parameter_id")
         REFERENCES "parameter" ("id"),

  CONSTRAINT "auction_id"
     FOREIGN KEY ("auction_id")
         REFERENCES "auction" ("id")

);

CREATE TABLE "auction_photo" (
  "id" SERIAL,
  "auction_id" INT,
  "photo" VARCHAR,
  "order" INT,
  "created_at" TIMESTAMP DEFAULT (now()),

  CONSTRAINT "auction_id"
     FOREIGN KEY ("auction_id")
         REFERENCES "auction" ("id")
);



