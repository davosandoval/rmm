--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2018-10-15 09:08:38 -05


--
-- TOC entry 3162 (class 0 OID 16799)
-- Dependencies: 199
-- Data for Name: customer; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.customer (id, "user") VALUES (1, 'pepe');
INSERT INTO dsandoval.customer (id, "user") VALUES (2, 'valentina');


--
-- TOC entry 3166 (class 0 OID 16825)
-- Dependencies: 203
-- Data for Name: operative_system; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.operative_system (id, description) VALUES ('WIN_WORKSTATION', 'Windows workstation');
INSERT INTO dsandoval.operative_system (id, description) VALUES ('WIN_SERVER', 'Windows Server');
INSERT INTO dsandoval.operative_system (id, description) VALUES ('MAC', 'MacBook');


--
-- TOC entry 3165 (class 0 OID 16819)
-- Dependencies: 202
-- Data for Name: device; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.device (id, name, fk_operative_system_id, fk_device_customer_id) VALUES (1, 'nueva de valen1', 'MAC', 2);
INSERT INTO dsandoval.device (id, name, fk_operative_system_id, fk_device_customer_id) VALUES (4, 'nueva de valen3', 'WIN_WORKSTATION', 2);
INSERT INTO dsandoval.device (id, name, fk_operative_system_id, fk_device_customer_id) VALUES (3, 'mac valen2', 'MAC', 2);
INSERT INTO dsandoval.device (id, name, fk_operative_system_id, fk_device_customer_id) VALUES (7, 'mac_eclipse', 'MAC', 2);
INSERT INTO dsandoval.device (id, name, fk_operative_system_id, fk_device_customer_id) VALUES (5, 'servidor1', 'WIN_SERVER', 2);


--
-- TOC entry 3163 (class 0 OID 16807)
-- Dependencies: 200
-- Data for Name: service; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.service (id, description) VALUES ('CLOUDBERRY', 'Backup system');
INSERT INTO dsandoval.service (id, description) VALUES ('PSA', 'Ticketing system');
INSERT INTO dsandoval.service (id, description) VALUES ('TEAMVIEWER', 'Remote connection');
INSERT INTO dsandoval.service (id, description) VALUES ('ANTIVIRUS', 'Antivirus');


--
-- TOC entry 3169 (class 0 OID 16866)
-- Dependencies: 206
-- Data for Name: service_cost; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.service_cost (id, cost) VALUES ('MAC_ANTIVIRUS', 7);
INSERT INTO dsandoval.service_cost (id, cost) VALUES ('WIN_ANTIVIRUS', 5);
INSERT INTO dsandoval.service_cost (id, cost) VALUES ('CLOUDBERRY', 3);
INSERT INTO dsandoval.service_cost (id, cost) VALUES ('PSA', 2);
INSERT INTO dsandoval.service_cost (id, cost) VALUES ('TEAMVIEWER', 1);
INSERT INTO dsandoval.service_cost (id, cost) VALUES ('REGISTERED_DEVICE', 4);


--
-- TOC entry 3168 (class 0 OID 16832)
-- Dependencies: 205
-- Data for Name: service_customer; Type: TABLE DATA; Schema: dsandoval; Owner: postgres
--

INSERT INTO dsandoval.service_customer (id, fk_customer_id, fk_service_id) VALUES (1, 2, 'ANTIVIRUS');
INSERT INTO dsandoval.service_customer (id, fk_customer_id, fk_service_id) VALUES (2, 2, 'TEAMVIEWER');
INSERT INTO dsandoval.service_customer (id, fk_customer_id, fk_service_id) VALUES (8, 2, 'PSA');


--
-- TOC entry 3175 (class 0 OID 0)
-- Dependencies: 198
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: dsandoval; Owner: postgres
--

SELECT pg_catalog.setval('dsandoval.customer_id_seq', 2, true);


--
-- TOC entry 3176 (class 0 OID 0)
-- Dependencies: 201
-- Name: device_id_seq; Type: SEQUENCE SET; Schema: dsandoval; Owner: postgres
--

SELECT pg_catalog.setval('dsandoval.device_id_seq', 7, true);


--
-- TOC entry 3177 (class 0 OID 0)
-- Dependencies: 204
-- Name: service_customer_id_seq; Type: SEQUENCE SET; Schema: dsandoval; Owner: postgres
--

SELECT pg_catalog.setval('dsandoval.service_customer_id_seq', 10, true);


-- Completed on 2018-10-15 09:08:38 -05

--
-- PostgreSQL database dump complete
--

