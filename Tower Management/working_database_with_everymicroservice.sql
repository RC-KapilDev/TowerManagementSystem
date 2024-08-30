toc.dat                                                                                             0000600 0004000 0002000 00000050014 14664407112 0014444 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP   
                    |           dev1    16.3    16.3 7    E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         H           1262    16716    dev1    DATABASE        CREATE DATABASE dev1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE dev1;
                postgres    false         �            1255    16749     generate_username_and_password()    FUNCTION     �  CREATE FUNCTION public.generate_username_and_password() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    random_suffix CHAR(3);
    random_number CHAR(4);
    base_name TEXT;
BEGIN
    -- Extract first 4 characters of the name
    base_name := LOWER(LEFT(NEW.name, 4));

    -- Generate a random 3-character suffix for username
    random_suffix := substr(md5(random()::text), 1, 3);

    -- Generate a random 4-digit number for password
    random_number := substr(md5(random()::text), 1, 4);

    -- Set the username and password
    NEW.username := base_name || random_suffix;
    NEW.password := base_name || random_number;

    RETURN NEW;
END;
$$;
 7   DROP FUNCTION public.generate_username_and_password();
       public          postgres    false         �            1255    16750    update_last_maintained()    FUNCTION       CREATE FUNCTION public.update_last_maintained() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- Update the last_maintained field in tower_info
    UPDATE tower_info
    SET last_maintained = NEW.completed_date
    WHERE tower_id = NEW.tower_id;

    RETURN NEW;
END;
$$;
 /   DROP FUNCTION public.update_last_maintained();
       public          postgres    false         �            1255    16751    update_updated_at_column()    FUNCTION     �   CREATE FUNCTION public.update_updated_at_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$;
 1   DROP FUNCTION public.update_updated_at_column();
       public          postgres    false         �            1255    16752    update_user_updated_at()    FUNCTION     �   CREATE FUNCTION public.update_user_updated_at() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at := NOW();  -- Set updated_at to the current timestamp
    RETURN NEW;
END;
$$;
 /   DROP FUNCTION public.update_user_updated_at();
       public          postgres    false         �            1255    16753    update_work_order_updated_at()    FUNCTION     �   CREATE FUNCTION public.update_work_order_updated_at() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at := NOW();  -- Set updated_at to the current timestamp
    RETURN NEW;
END;
$$;
 5   DROP FUNCTION public.update_work_order_updated_at();
       public          postgres    false         �            1259    16754    equipment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.equipment_id_seq
    START WITH 80000000
    INCREMENT BY 1
    MINVALUE 80000000
    MAXVALUE 89999999
    CACHE 1;
 '   DROP SEQUENCE public.equipment_id_seq;
       public          postgres    false         �            1259    16755 	   equipment    TABLE     �  CREATE TABLE public.equipment (
    equipment_id integer DEFAULT nextval('public.equipment_id_seq'::regclass) NOT NULL,
    workorder_id integer,
    tower_id integer,
    serial_number integer,
    manufacture text,
    model text,
    created_at timestamp without time zone DEFAULT now(),
    equipment_name text,
    deleted_status boolean DEFAULT false,
    claimed boolean DEFAULT false
);
    DROP TABLE public.equipment;
       public         heap    postgres    false    215         �            1259    16764    maintenance_id_seq    SEQUENCE     �   CREATE SEQUENCE public.maintenance_id_seq
    START WITH 7000000
    INCREMENT BY 1
    MINVALUE 7000000
    MAXVALUE 7999999
    CACHE 1;
 )   DROP SEQUENCE public.maintenance_id_seq;
       public          postgres    false         �            1259    16765    maintenance_report    TABLE     w  CREATE TABLE public.maintenance_report (
    maintenance_id integer DEFAULT nextval('public.maintenance_id_seq'::regclass) NOT NULL,
    user_id integer,
    workorder_id integer,
    tower_id integer,
    equipment_required text,
    issues_faced text,
    priority text,
    created_at timestamp without time zone DEFAULT now(),
    deleted_status boolean DEFAULT false
);
 &   DROP TABLE public.maintenance_report;
       public         heap    postgres    false    217         �            1259    16773    notification_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notification_id_seq;
       public          postgres    false         �            1259    16774    notifications    TABLE     r  CREATE TABLE public.notifications (
    notification_id integer DEFAULT nextval('public.notification_id_seq'::regclass) NOT NULL,
    sender_id integer,
    receiver_id integer,
    subject text NOT NULL,
    message text NOT NULL,
    sent_at timestamp without time zone DEFAULT now(),
    read_status boolean DEFAULT false,
    deleted_status boolean DEFAULT false
);
 !   DROP TABLE public.notifications;
       public         heap    postgres    false    219         �            1259    16783    tower_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tower_id_seq
    START WITH 400001
    INCREMENT BY 1
    MINVALUE 400001
    MAXVALUE 999999
    CACHE 1;
 #   DROP SEQUENCE public.tower_id_seq;
       public          postgres    false         �            1259    16784 
   tower_info    TABLE       CREATE TABLE public.tower_info (
    tower_id integer DEFAULT nextval('public.tower_id_seq'::regclass) NOT NULL,
    location text,
    height double precision,
    type text,
    status text,
    pincode integer,
    latitude double precision,
    longitude double precision,
    power_reading integer DEFAULT 0,
    fuel_reading numeric DEFAULT 0,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    last_maintained date,
    deleted_status boolean DEFAULT false
);
    DROP TABLE public.tower_info;
       public         heap    postgres    false    221         �            1259    16795    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    START WITH 500001
    INCREMENT BY 1
    MINVALUE 500001
    MAXVALUE 599999
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false         �            1259    16796    users    TABLE     O  CREATE TABLE public.users (
    user_id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    email text NOT NULL,
    specialisation text,
    location text,
    pincode integer,
    active_status boolean DEFAULT true,
    deleted_status boolean DEFAULT false,
    username text NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    password text NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    role text,
    CONSTRAINT chk_email_format CHECK ((email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false    223         �            1259    16807    workorder_id_seq    SEQUENCE     �   CREATE SEQUENCE public.workorder_id_seq
    START WITH 200
    INCREMENT BY 1
    MINVALUE 200
    MAXVALUE 400000
    CACHE 1;
 '   DROP SEQUENCE public.workorder_id_seq;
       public          postgres    false         �            1259    16808 
   work_order    TABLE     �  CREATE TABLE public.work_order (
    workorder_id integer DEFAULT nextval('public.workorder_id_seq'::regclass) NOT NULL,
    tower_id integer,
    user_id integer,
    task_description text,
    scheduled_date date DEFAULT CURRENT_DATE,
    status text,
    completed_date timestamp without time zone,
    created_at timestamp without time zone DEFAULT now(),
    deleted_status boolean DEFAULT false,
    updated_at timestamp without time zone DEFAULT now()
);
    DROP TABLE public.work_order;
       public         heap    postgres    false    225         8          0    16755 	   equipment 
   TABLE DATA           �   COPY public.equipment (equipment_id, workorder_id, tower_id, serial_number, manufacture, model, created_at, equipment_name, deleted_status, claimed) FROM stdin;
    public          postgres    false    216       4920.dat :          0    16765    maintenance_report 
   TABLE DATA           �   COPY public.maintenance_report (maintenance_id, user_id, workorder_id, tower_id, equipment_required, issues_faced, priority, created_at, deleted_status) FROM stdin;
    public          postgres    false    218       4922.dat <          0    16774    notifications 
   TABLE DATA           �   COPY public.notifications (notification_id, sender_id, receiver_id, subject, message, sent_at, read_status, deleted_status) FROM stdin;
    public          postgres    false    220       4924.dat >          0    16784 
   tower_info 
   TABLE DATA           �   COPY public.tower_info (tower_id, location, height, type, status, pincode, latitude, longitude, power_reading, fuel_reading, created_at, updated_at, last_maintained, deleted_status) FROM stdin;
    public          postgres    false    222       4926.dat @          0    16796    users 
   TABLE DATA           �   COPY public.users (user_id, name, email, specialisation, location, pincode, active_status, deleted_status, username, created_at, password, updated_at, role) FROM stdin;
    public          postgres    false    224       4928.dat B          0    16808 
   work_order 
   TABLE DATA           �   COPY public.work_order (workorder_id, tower_id, user_id, task_description, scheduled_date, status, completed_date, created_at, deleted_status, updated_at) FROM stdin;
    public          postgres    false    226       4930.dat I           0    0    equipment_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.equipment_id_seq', 80000011, true);
          public          postgres    false    215         J           0    0    maintenance_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.maintenance_id_seq', 7000030, true);
          public          postgres    false    217         K           0    0    notification_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.notification_id_seq', 5, true);
          public          postgres    false    219         L           0    0    tower_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.tower_id_seq', 400014, true);
          public          postgres    false    221         M           0    0    user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_id_seq', 500012, true);
          public          postgres    false    223         N           0    0    workorder_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.workorder_id_seq', 237, true);
          public          postgres    false    225         �           2606    16820    equipment equipment_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (equipment_id);
 B   ALTER TABLE ONLY public.equipment DROP CONSTRAINT equipment_pkey;
       public            postgres    false    216         �           2606    16822 *   maintenance_report maintenance_report_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_pkey PRIMARY KEY (maintenance_id);
 T   ALTER TABLE ONLY public.maintenance_report DROP CONSTRAINT maintenance_report_pkey;
       public            postgres    false    218         �           2606    16824     notifications notifications_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (notification_id);
 J   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_pkey;
       public            postgres    false    220         �           2606    16826    tower_info tower_info_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tower_info
    ADD CONSTRAINT tower_info_pkey PRIMARY KEY (tower_id);
 D   ALTER TABLE ONLY public.tower_info DROP CONSTRAINT tower_info_pkey;
       public            postgres    false    222         �           2606    16828    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    224         �           2606    16830    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    224         �           2606    16832    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    224         �           2606    16834    work_order work_order_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_pkey PRIMARY KEY (workorder_id);
 D   ALTER TABLE ONLY public.work_order DROP CONSTRAINT work_order_pkey;
       public            postgres    false    226         �           2620    16835     users generate_username_password    TRIGGER     �   CREATE TRIGGER generate_username_password BEFORE INSERT ON public.users FOR EACH ROW EXECUTE FUNCTION public.generate_username_and_password();
 9   DROP TRIGGER generate_username_password ON public.users;
       public          postgres    false    227    224         �           2620    16836 +   work_order trg_update_work_order_updated_at    TRIGGER     �   CREATE TRIGGER trg_update_work_order_updated_at BEFORE UPDATE ON public.work_order FOR EACH ROW EXECUTE FUNCTION public.update_work_order_updated_at();
 D   DROP TRIGGER trg_update_work_order_updated_at ON public.work_order;
       public          postgres    false    231    226         �           2620    16837 )   work_order update_last_maintained_trigger    TRIGGER     �   CREATE TRIGGER update_last_maintained_trigger AFTER UPDATE OF completed_date ON public.work_order FOR EACH ROW EXECUTE FUNCTION public.update_last_maintained();
 B   DROP TRIGGER update_last_maintained_trigger ON public.work_order;
       public          postgres    false    226    226    228         �           2620    16838 '   tower_info update_tower_info_updated_at    TRIGGER     �   CREATE TRIGGER update_tower_info_updated_at BEFORE UPDATE ON public.tower_info FOR EACH ROW EXECUTE FUNCTION public.update_updated_at_column();
 @   DROP TRIGGER update_tower_info_updated_at ON public.tower_info;
       public          postgres    false    229    222         �           2620    16839    users update_user_updated_at    TRIGGER     �   CREATE TRIGGER update_user_updated_at BEFORE UPDATE ON public.users FOR EACH ROW EXECUTE FUNCTION public.update_user_updated_at();
 5   DROP TRIGGER update_user_updated_at ON public.users;
       public          postgres    false    230    224         �           2606    16840 !   equipment equipment_tower_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.equipment DROP CONSTRAINT equipment_tower_id_fkey;
       public          postgres    false    216    4753    222         �           2606    16845 %   equipment equipment_workorder_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_workorder_id_fkey FOREIGN KEY (workorder_id) REFERENCES public.work_order(workorder_id) ON DELETE CASCADE;
 O   ALTER TABLE ONLY public.equipment DROP CONSTRAINT equipment_workorder_id_fkey;
       public          postgres    false    226    216    4761         �           2606    16850 3   maintenance_report maintenance_report_tower_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.maintenance_report DROP CONSTRAINT maintenance_report_tower_id_fkey;
       public          postgres    false    218    4753    222         �           2606    16855 2   maintenance_report maintenance_report_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.maintenance_report DROP CONSTRAINT maintenance_report_user_id_fkey;
       public          postgres    false    218    4757    224         �           2606    16860 7   maintenance_report maintenance_report_workorder_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_workorder_id_fkey FOREIGN KEY (workorder_id) REFERENCES public.work_order(workorder_id) ON DELETE CASCADE;
 a   ALTER TABLE ONLY public.maintenance_report DROP CONSTRAINT maintenance_report_workorder_id_fkey;
       public          postgres    false    4761    226    218         �           2606    16865 ,   notifications notifications_receiver_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_receiver_id_fkey FOREIGN KEY (receiver_id) REFERENCES public.users(user_id) ON DELETE SET NULL;
 V   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_receiver_id_fkey;
       public          postgres    false    220    224    4757         �           2606    16870 *   notifications notifications_sender_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id) ON DELETE SET NULL;
 T   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_sender_id_fkey;
       public          postgres    false    224    220    4757         �           2606    16875 #   work_order work_order_tower_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.work_order DROP CONSTRAINT work_order_tower_id_fkey;
       public          postgres    false    226    222    4753         �           2606    16880 "   work_order work_order_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.work_order DROP CONSTRAINT work_order_user_id_fkey;
       public          postgres    false    4757    226    224                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            4920.dat                                                                                            0000600 0004000 0002000 00000002010 14664407112 0014246 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        80000001	221	400001	80000001	Acme Corp	Model A	2024-08-29 15:27:48.246932	Signal Booster	f	f
80000002	222	400011	80000002	TechPro	Model B	2024-08-29 15:27:48.246932	Power Supply Unit	f	f
80000003	223	400003	80000003	ElectroMax	Model C	2024-08-29 15:27:48.246932	Antenna	f	f
80000004	224	400004	80000004	NetGear	Model D	2024-08-29 15:27:48.246932	Router	f	f
80000005	225	400005	80000005	SkyTech	Model E	2024-08-29 15:27:48.246932	Transmitter	f	f
80000006	226	400006	80000006	PowerTech	Model F	2024-08-29 15:27:48.246932	Backup Generator	f	f
80000007	227	400007	80000007	SignalCo	Model G	2024-08-29 15:27:48.246932	Amplifier	f	f
80000008	228	400008	80000008	GridCom	Model H	2024-08-29 15:27:48.246932	Cable	f	f
80000009	229	400009	80000009	WaveLink	Model I	2024-08-29 15:27:48.246932	Battery Pack	f	f
80000010	230	400010	80000010	StarNet	Model J	2024-08-29 15:27:48.246932	Modem	f	f
80000011	200	400003	12345	ABC	X100	2024-08-30 10:12:06.216142	Router	f	f
80000000	200	400001	67890	XYZ	Y200	2024-08-29 12:56:16.704646	Switch	t	t
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        4922.dat                                                                                            0000600 0004000 0002000 00000002040 14664407112 0014253 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        7000000	500001	200	400001	Spare parts	Signal failure	high	2024-08-29 12:52:27.645147	f
7000021	500001	221	400001	Replacement cables	Signal dropouts	high	2024-08-29 15:26:57.985239	f
7000022	500002	222	400011	New batteries	Power outage	moderate	2024-08-29 15:26:57.985239	f
7000023	500003	223	400003	Software update tools	System lag	lowest	2024-08-29 15:26:57.985239	f
7000024	500004	224	400004	Cleaning materials	Dust accumulation	lowest	2024-08-29 15:26:57.985239	f
7000025	500005	225	400005	Backup generator	Power instability	high	2024-08-29 15:26:57.985239	f
7000026	500006	226	400006	Calibration equipment	Inconsistent signal	moderate	2024-08-29 15:26:57.985239	f
7000027	500007	227	400007	Replacement parts	Hardware malfunction	high	2024-08-29 15:26:57.985239	f
7000028	500008	228	400008	Safety gear	Safety compliance	lowest	2024-08-29 15:26:57.985239	f
7000029	500009	229	400009	Inspection tools	Maintenance oversight	moderate	2024-08-29 15:26:57.985239	f
7000030	500010	230	400010	Access tools	Access issues	lowest	2024-08-29 15:26:57.985239	f
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                4924.dat                                                                                            0000600 0004000 0002000 00000000612 14664407112 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        2	500001	500002	Meeting Reminder	Dont forget about the meeting at 3 PM.	2024-08-29 14:46:38.122814	t	f
3	500001	500002	Project Update	Please update the project by EOD.	2024-08-29 10:08:43.07848	f	t
5	500001	500002	Project Update	Please update the project by EOD.	2024-08-29 11:12:00.788897	f	f
4	500001	500002	Project Update	Please update the project by EOD.	2024-08-29 11:11:58.07034	f	t
\.


                                                                                                                      4926.dat                                                                                            0000600 0004000 0002000 00000002742 14664407112 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        400003	Chennai	150.5	4G	active	600001	13.0827	80.2707	0	0	2024-08-29 12:44:27.489862	2024-08-29 12:44:27.489862	2024-08-01	f
400004	Delhi	120	5G	active	110001	28.6139	77.209	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-07-15	f
400005	Mumbai	95	4G	inactive	400001	19.076	72.8777	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-07-10	f
400006	Kolkata	110	3G	active	700001	22.5726	88.3639	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-07-20	f
400007	Bangalore	130	5G	under-maintenance	560001	12.9716	77.5946	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-01	f
400008	Hyderabad	140	4G	active	500001	17.385	78.4867	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-10	f
400009	Chennai	150	3G	inactive	600001	13.0827	80.2707	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-05	f
400010	Pune	100	4G	under-maintenance	411001	18.5204	73.8567	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-15	f
400011	Ahmedabad	110	5G	active	380001	23.0225	72.5714	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-20	f
400012	Jaipur	115	3G	active	302001	26.9124	75.7873	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-25	f
400013	Surat	125	4G	inactive	395001	21.1702	72.8311	0	0	2024-08-29 15:17:54.522464	2024-08-29 15:17:54.522464	2024-08-30	f
400001	Chennai	150.5	4G	active	600001	13.0827	80.2707	0	0	2024-08-29 12:43:08.274761	2024-08-30 11:22:15.228722	2024-09-01	f
\.


                              4928.dat                                                                                            0000600 0004000 0002000 00000003747 14664407112 0014300 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        500001	John Doe	new.email@example.com	Electrical Engineering	Chennai	600001	t	f	johnf4c	2024-08-29 12:31:26.182184	john3724	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500002	kapidlev	kapildev@example.com	Electrical Engineering	Chennai	600042	t	f	kapia86	2024-08-29 14:46:10.298533	kapi481e	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500003	Alice Smith	alice.smith@example.com	Network Engineering	Mumbai	400001	t	f	alice12	2024-08-29 15:18:53.906695	alic772b	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500004	Bob Johnson	bob.johnson@example.com	Telecom Systems	Delhi	110001	t	f	bob d56	2024-08-29 15:18:53.906695	bob e081	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500005	Charlie Brown	charlie.brown@example.com	Installation	Bangalore	560001	t	f	charc2e	2024-08-29 15:18:53.906695	charc4d1	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500006	Diana Prince	diana.prince@example.com	Maintenance	Hyderabad	500001	t	f	dian1bd	2024-08-29 15:18:53.906695	dian28ae	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500007	Ethan Hunt	ethan.hunt@example.com	Electrical Engineering	Chennai	600001	t	f	ethaed7	2024-08-29 15:18:53.906695	etha061a	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500008	Fiona Gallagher	fiona.gallagher@example.com	Technical Support	Kolkata	700001	t	f	fione23	2024-08-29 15:18:53.906695	fione38b	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500009	George Stark	george.stark@example.com	Tower Design	Pune	411001	t	f	geor787	2024-08-29 15:18:53.906695	geor5a63	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500010	Hannah Montana	hannah.montana@example.com	Field Operations	Ahmedabad	380001	t	f	hanna04	2024-08-29 15:18:53.906695	hanneb27	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500011	Ian Wright	ian.wright@example.com	Site Surveys	Jaipur	302001	t	f	ian d76	2024-08-29 15:18:53.906695	ian 8ddc	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
500012	Jasmine Patel	jasmine.patel@example.com	Network Operations	Surat	395001	t	f	jasm426	2024-08-29 15:18:53.906695	jasm996f	2024-08-29 09:37:46.341421	FIELD_TECHNICIAN
\.


                         4930.dat                                                                                            0000600 0004000 0002000 00000003553 14664407112 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        200	400001	500001	New task description	2024-08-29	pending	\N	2024-08-29 12:50:06.150607	f	2024-08-29 12:50:39.719445
222	400011	500002	Replace faulty cables	2024-09-02	completed	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
223	400003	500003	Upgrade software	2024-09-03	pending	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
224	400004	500004	Clean tower equipment	2024-09-04	completed	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
225	400005	500005	Check backup power systems	2024-09-05	pending	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
226	400006	500006	Test signal strength	2024-09-06	completed	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
227	400007	500007	Replace damaged parts	2024-09-07	pending	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
228	400008	500008	Conduct safety inspection	2024-09-08	completed	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
229	400009	500009	Review maintenance logs	2024-09-09	pending	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
230	400010	500010	Verify site access	2024-09-10	completed	\N	2024-08-29 15:21:18.614013	f	2024-08-29 15:21:18.614013
235	400001	500002	Inspect the tower for maintenance.	2024-09-01	PENDING	\N	2024-08-30 09:00:08.517586	f	2024-08-30 09:00:08.517586
236	400001	500002	Inspect the tower for maintenance.	2024-09-01	PENDING	\N	2024-08-30 09:03:38.256538	f	2024-08-30 09:03:38.256538
237	400001	500002	Inspect the tower for maintenance.	2024-09-01	COMPLETED	2024-09-01 15:30:00	2024-08-30 09:04:54.38937	f	2024-08-30 09:20:18.044716
1	400001	500001	Update task description.	2024-09-02	"COMPLETED"\r\n	2024-08-29 15:38:41.133037	2024-08-29 15:38:41.133037	t	2024-08-30 11:10:06.311687
221	400001	500001	Inspect antenna alignment	2024-09-01	COMPLETED	2024-09-01 15:30:00	2024-08-29 15:21:18.614013	f	2024-08-30 11:22:15.228722
\.


                                                                                                                                                     restore.sql                                                                                         0000600 0004000 0002000 00000042360 14664407112 0015376 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE dev1;
--
-- Name: dev1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE dev1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE dev1 OWNER TO postgres;

\connect dev1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: generate_username_and_password(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.generate_username_and_password() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    random_suffix CHAR(3);
    random_number CHAR(4);
    base_name TEXT;
BEGIN
    -- Extract first 4 characters of the name
    base_name := LOWER(LEFT(NEW.name, 4));

    -- Generate a random 3-character suffix for username
    random_suffix := substr(md5(random()::text), 1, 3);

    -- Generate a random 4-digit number for password
    random_number := substr(md5(random()::text), 1, 4);

    -- Set the username and password
    NEW.username := base_name || random_suffix;
    NEW.password := base_name || random_number;

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.generate_username_and_password() OWNER TO postgres;

--
-- Name: update_last_maintained(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_last_maintained() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- Update the last_maintained field in tower_info
    UPDATE tower_info
    SET last_maintained = NEW.completed_date
    WHERE tower_id = NEW.tower_id;

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_last_maintained() OWNER TO postgres;

--
-- Name: update_updated_at_column(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_updated_at_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_updated_at_column() OWNER TO postgres;

--
-- Name: update_user_updated_at(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_user_updated_at() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at := NOW();  -- Set updated_at to the current timestamp
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_user_updated_at() OWNER TO postgres;

--
-- Name: update_work_order_updated_at(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_work_order_updated_at() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at := NOW();  -- Set updated_at to the current timestamp
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_work_order_updated_at() OWNER TO postgres;

--
-- Name: equipment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipment_id_seq
    START WITH 80000000
    INCREMENT BY 1
    MINVALUE 80000000
    MAXVALUE 89999999
    CACHE 1;


ALTER SEQUENCE public.equipment_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment (
    equipment_id integer DEFAULT nextval('public.equipment_id_seq'::regclass) NOT NULL,
    workorder_id integer,
    tower_id integer,
    serial_number integer,
    manufacture text,
    model text,
    created_at timestamp without time zone DEFAULT now(),
    equipment_name text,
    deleted_status boolean DEFAULT false,
    claimed boolean DEFAULT false
);


ALTER TABLE public.equipment OWNER TO postgres;

--
-- Name: maintenance_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.maintenance_id_seq
    START WITH 7000000
    INCREMENT BY 1
    MINVALUE 7000000
    MAXVALUE 7999999
    CACHE 1;


ALTER SEQUENCE public.maintenance_id_seq OWNER TO postgres;

--
-- Name: maintenance_report; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.maintenance_report (
    maintenance_id integer DEFAULT nextval('public.maintenance_id_seq'::regclass) NOT NULL,
    user_id integer,
    workorder_id integer,
    tower_id integer,
    equipment_required text,
    issues_faced text,
    priority text,
    created_at timestamp without time zone DEFAULT now(),
    deleted_status boolean DEFAULT false
);


ALTER TABLE public.maintenance_report OWNER TO postgres;

--
-- Name: notification_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.notification_id_seq OWNER TO postgres;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    notification_id integer DEFAULT nextval('public.notification_id_seq'::regclass) NOT NULL,
    sender_id integer,
    receiver_id integer,
    subject text NOT NULL,
    message text NOT NULL,
    sent_at timestamp without time zone DEFAULT now(),
    read_status boolean DEFAULT false,
    deleted_status boolean DEFAULT false
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: tower_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tower_id_seq
    START WITH 400001
    INCREMENT BY 1
    MINVALUE 400001
    MAXVALUE 999999
    CACHE 1;


ALTER SEQUENCE public.tower_id_seq OWNER TO postgres;

--
-- Name: tower_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tower_info (
    tower_id integer DEFAULT nextval('public.tower_id_seq'::regclass) NOT NULL,
    location text,
    height double precision,
    type text,
    status text,
    pincode integer,
    latitude double precision,
    longitude double precision,
    power_reading integer DEFAULT 0,
    fuel_reading numeric DEFAULT 0,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    last_maintained date,
    deleted_status boolean DEFAULT false
);


ALTER TABLE public.tower_info OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 500001
    INCREMENT BY 1
    MINVALUE 500001
    MAXVALUE 599999
    CACHE 1;


ALTER SEQUENCE public.user_id_seq OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    email text NOT NULL,
    specialisation text,
    location text,
    pincode integer,
    active_status boolean DEFAULT true,
    deleted_status boolean DEFAULT false,
    username text NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    password text NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    role text,
    CONSTRAINT chk_email_format CHECK ((email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'::text))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: workorder_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workorder_id_seq
    START WITH 200
    INCREMENT BY 1
    MINVALUE 200
    MAXVALUE 400000
    CACHE 1;


ALTER SEQUENCE public.workorder_id_seq OWNER TO postgres;

--
-- Name: work_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.work_order (
    workorder_id integer DEFAULT nextval('public.workorder_id_seq'::regclass) NOT NULL,
    tower_id integer,
    user_id integer,
    task_description text,
    scheduled_date date DEFAULT CURRENT_DATE,
    status text,
    completed_date timestamp without time zone,
    created_at timestamp without time zone DEFAULT now(),
    deleted_status boolean DEFAULT false,
    updated_at timestamp without time zone DEFAULT now()
);


ALTER TABLE public.work_order OWNER TO postgres;

--
-- Data for Name: equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipment (equipment_id, workorder_id, tower_id, serial_number, manufacture, model, created_at, equipment_name, deleted_status, claimed) FROM stdin;
\.
COPY public.equipment (equipment_id, workorder_id, tower_id, serial_number, manufacture, model, created_at, equipment_name, deleted_status, claimed) FROM '$$PATH$$/4920.dat';

--
-- Data for Name: maintenance_report; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.maintenance_report (maintenance_id, user_id, workorder_id, tower_id, equipment_required, issues_faced, priority, created_at, deleted_status) FROM stdin;
\.
COPY public.maintenance_report (maintenance_id, user_id, workorder_id, tower_id, equipment_required, issues_faced, priority, created_at, deleted_status) FROM '$$PATH$$/4922.dat';

--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notifications (notification_id, sender_id, receiver_id, subject, message, sent_at, read_status, deleted_status) FROM stdin;
\.
COPY public.notifications (notification_id, sender_id, receiver_id, subject, message, sent_at, read_status, deleted_status) FROM '$$PATH$$/4924.dat';

--
-- Data for Name: tower_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tower_info (tower_id, location, height, type, status, pincode, latitude, longitude, power_reading, fuel_reading, created_at, updated_at, last_maintained, deleted_status) FROM stdin;
\.
COPY public.tower_info (tower_id, location, height, type, status, pincode, latitude, longitude, power_reading, fuel_reading, created_at, updated_at, last_maintained, deleted_status) FROM '$$PATH$$/4926.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, name, email, specialisation, location, pincode, active_status, deleted_status, username, created_at, password, updated_at, role) FROM stdin;
\.
COPY public.users (user_id, name, email, specialisation, location, pincode, active_status, deleted_status, username, created_at, password, updated_at, role) FROM '$$PATH$$/4928.dat';

--
-- Data for Name: work_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.work_order (workorder_id, tower_id, user_id, task_description, scheduled_date, status, completed_date, created_at, deleted_status, updated_at) FROM stdin;
\.
COPY public.work_order (workorder_id, tower_id, user_id, task_description, scheduled_date, status, completed_date, created_at, deleted_status, updated_at) FROM '$$PATH$$/4930.dat';

--
-- Name: equipment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipment_id_seq', 80000011, true);


--
-- Name: maintenance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.maintenance_id_seq', 7000030, true);


--
-- Name: notification_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notification_id_seq', 5, true);


--
-- Name: tower_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tower_id_seq', 400014, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 500012, true);


--
-- Name: workorder_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workorder_id_seq', 237, true);


--
-- Name: equipment equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (equipment_id);


--
-- Name: maintenance_report maintenance_report_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_pkey PRIMARY KEY (maintenance_id);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (notification_id);


--
-- Name: tower_info tower_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tower_info
    ADD CONSTRAINT tower_info_pkey PRIMARY KEY (tower_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: work_order work_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_pkey PRIMARY KEY (workorder_id);


--
-- Name: users generate_username_password; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER generate_username_password BEFORE INSERT ON public.users FOR EACH ROW EXECUTE FUNCTION public.generate_username_and_password();


--
-- Name: work_order trg_update_work_order_updated_at; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_update_work_order_updated_at BEFORE UPDATE ON public.work_order FOR EACH ROW EXECUTE FUNCTION public.update_work_order_updated_at();


--
-- Name: work_order update_last_maintained_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_last_maintained_trigger AFTER UPDATE OF completed_date ON public.work_order FOR EACH ROW EXECUTE FUNCTION public.update_last_maintained();


--
-- Name: tower_info update_tower_info_updated_at; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_tower_info_updated_at BEFORE UPDATE ON public.tower_info FOR EACH ROW EXECUTE FUNCTION public.update_updated_at_column();


--
-- Name: users update_user_updated_at; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_user_updated_at BEFORE UPDATE ON public.users FOR EACH ROW EXECUTE FUNCTION public.update_user_updated_at();


--
-- Name: equipment equipment_tower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;


--
-- Name: equipment equipment_workorder_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_workorder_id_fkey FOREIGN KEY (workorder_id) REFERENCES public.work_order(workorder_id) ON DELETE CASCADE;


--
-- Name: maintenance_report maintenance_report_tower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;


--
-- Name: maintenance_report maintenance_report_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: maintenance_report maintenance_report_workorder_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_report
    ADD CONSTRAINT maintenance_report_workorder_id_fkey FOREIGN KEY (workorder_id) REFERENCES public.work_order(workorder_id) ON DELETE CASCADE;


--
-- Name: notifications notifications_receiver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_receiver_id_fkey FOREIGN KEY (receiver_id) REFERENCES public.users(user_id) ON DELETE SET NULL;


--
-- Name: notifications notifications_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(user_id) ON DELETE SET NULL;


--
-- Name: work_order work_order_tower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_tower_id_fkey FOREIGN KEY (tower_id) REFERENCES public.tower_info(tower_id) ON DELETE CASCADE;


--
-- Name: work_order work_order_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_order
    ADD CONSTRAINT work_order_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                