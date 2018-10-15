

CREATE SCHEMA dsandoval;


ALTER SCHEMA dsandoval OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 16799)
-- Name: customer; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.customer (
    id integer NOT NULL,
    "user" character varying(100) NOT NULL
);


ALTER TABLE dsandoval.customer OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16797)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: dsandoval; Owner: postgres
--

CREATE SEQUENCE dsandoval.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dsandoval.customer_id_seq OWNER TO postgres;

--
-- TOC entry 3166 (class 0 OID 0)
-- Dependencies: 198
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: dsandoval; Owner: postgres
--

ALTER SEQUENCE dsandoval.customer_id_seq OWNED BY dsandoval.customer.id;


--
-- TOC entry 202 (class 1259 OID 16819)
-- Name: device; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.device (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    fk_operative_system_id character varying(100) NOT NULL,
    fk_device_customer_id integer NOT NULL
);


ALTER TABLE dsandoval.device OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16817)
-- Name: device_id_seq; Type: SEQUENCE; Schema: dsandoval; Owner: postgres
--

CREATE SEQUENCE dsandoval.device_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dsandoval.device_id_seq OWNER TO postgres;

--
-- TOC entry 3167 (class 0 OID 0)
-- Dependencies: 201
-- Name: device_id_seq; Type: SEQUENCE OWNED BY; Schema: dsandoval; Owner: postgres
--

ALTER SEQUENCE dsandoval.device_id_seq OWNED BY dsandoval.device.id;


--
-- TOC entry 203 (class 1259 OID 16825)
-- Name: operative_system; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.operative_system (
    id character varying(100) NOT NULL,
    description character varying(100) NOT NULL
);


ALTER TABLE dsandoval.operative_system OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16807)
-- Name: service; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.service (
    id character varying(100) NOT NULL,
    description character varying(100) NOT NULL
);


ALTER TABLE dsandoval.service OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16866)
-- Name: service_cost; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.service_cost (
    id character varying(100) NOT NULL,
    cost numeric NOT NULL
);


ALTER TABLE dsandoval.service_cost OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16832)
-- Name: service_customer; Type: TABLE; Schema: dsandoval; Owner: postgres
--

CREATE TABLE dsandoval.service_customer (
    id integer NOT NULL,
    fk_customer_id integer NOT NULL,
    fk_service_id character varying(100) NOT NULL
);


ALTER TABLE dsandoval.service_customer OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16830)
-- Name: service_customer_id_seq; Type: SEQUENCE; Schema: dsandoval; Owner: postgres
--

CREATE SEQUENCE dsandoval.service_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dsandoval.service_customer_id_seq OWNER TO postgres;

--
-- TOC entry 3168 (class 0 OID 0)
-- Dependencies: 204
-- Name: service_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: dsandoval; Owner: postgres
--

ALTER SEQUENCE dsandoval.service_customer_id_seq OWNED BY dsandoval.service_customer.id;


--
-- TOC entry 3015 (class 2604 OID 16802)
-- Name: customer id; Type: DEFAULT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.customer ALTER COLUMN id SET DEFAULT nextval('dsandoval.customer_id_seq'::regclass);


--
-- TOC entry 3016 (class 2604 OID 16822)
-- Name: device id; Type: DEFAULT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.device ALTER COLUMN id SET DEFAULT nextval('dsandoval.device_id_seq'::regclass);


--
-- TOC entry 3017 (class 2604 OID 16835)
-- Name: service_customer id; Type: DEFAULT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_customer ALTER COLUMN id SET DEFAULT nextval('dsandoval.service_customer_id_seq'::regclass);


--
-- TOC entry 3019 (class 2606 OID 16804)
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (id);


--
-- TOC entry 3021 (class 2606 OID 16806)
-- Name: customer customer_user_key; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.customer
    ADD CONSTRAINT customer_user_key UNIQUE ("user");


--
-- TOC entry 3027 (class 2606 OID 16824)
-- Name: device device_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.device
    ADD CONSTRAINT device_pk PRIMARY KEY (id);


--
-- TOC entry 3029 (class 2606 OID 16829)
-- Name: operative_system operative_system_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.operative_system
    ADD CONSTRAINT operative_system_pk PRIMARY KEY (id);


--
-- TOC entry 3035 (class 2606 OID 16873)
-- Name: service_cost service_cost_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_cost
    ADD CONSTRAINT service_cost_pk PRIMARY KEY (id);


--
-- TOC entry 3031 (class 2606 OID 16837)
-- Name: service_customer service_customer_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_customer
    ADD CONSTRAINT service_customer_pk PRIMARY KEY (id);


--
-- TOC entry 3023 (class 2606 OID 16816)
-- Name: service service_description_key; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service
    ADD CONSTRAINT service_description_key UNIQUE (description);


--
-- TOC entry 3025 (class 2606 OID 16814)
-- Name: service service_pk; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service
    ADD CONSTRAINT service_pk PRIMARY KEY (id);


--
-- TOC entry 3033 (class 2606 OID 16859)
-- Name: service_customer uq_service_customer; Type: CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_customer
    ADD CONSTRAINT uq_service_customer UNIQUE (fk_customer_id, fk_service_id);

ALTER TABLE ONLY dsandoval.device
    ADD CONSTRAINT uq_device_name UNIQUE (name);


--
-- TOC entry 3036 (class 2606 OID 16838)
-- Name: device device_fk0; Type: FK CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.device
    ADD CONSTRAINT device_fk0 FOREIGN KEY (fk_operative_system_id) REFERENCES dsandoval.operative_system(id);


--
-- TOC entry 3037 (class 2606 OID 16843)
-- Name: device device_fk1; Type: FK CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.device
    ADD CONSTRAINT device_fk1 FOREIGN KEY (fk_device_customer_id) REFERENCES dsandoval.customer(id);


--
-- TOC entry 3038 (class 2606 OID 16848)
-- Name: service_customer service_customer_fk0; Type: FK CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_customer
    ADD CONSTRAINT service_customer_fk0 FOREIGN KEY (fk_customer_id) REFERENCES dsandoval.customer(id);


--
-- TOC entry 3039 (class 2606 OID 16853)
-- Name: service_customer service_customer_fk1; Type: FK CONSTRAINT; Schema: dsandoval; Owner: postgres
--

ALTER TABLE ONLY dsandoval.service_customer
    ADD CONSTRAINT service_customer_fk1 FOREIGN KEY (fk_service_id) REFERENCES dsandoval.service(id);


-- Completed on 2018-10-15 09:05:09 -05

--
-- PostgreSQL database dump complete
--

