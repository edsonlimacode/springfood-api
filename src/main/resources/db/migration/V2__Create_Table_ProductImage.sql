CREATE TABLE public.product_image (
	product_id int8 NULL,
	"name" varchar NULL,
	"type" varchar NULL,
	"size" int8 NULL,
	CONSTRAINT product_image_pk PRIMARY KEY (product_id),
	CONSTRAINT product_image_products_fk FOREIGN KEY (product_id) REFERENCES public.products(id) ON DELETE CASCADE
);
