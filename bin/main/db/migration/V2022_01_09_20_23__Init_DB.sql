drop table public.info;

create table public.info(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT,
    currency text,
    price float,
    high float,
    low float,
    open float,
    close float,
    time text
);

CREATE UNIQUE INDEX info_id_uindex ON public.info(id);