PGDMP                         u            db_proinsalud    9.6.3    9.6.3 �    	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            	           1262    67230    db_proinsalud    DATABASE     �   CREATE DATABASE db_proinsalud WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE db_proinsalud;
             postgres    false                        2615    67233    audit    SCHEMA        CREATE SCHEMA audit;
    DROP SCHEMA audit;
             postgres    false            	           0    0    audit    ACL     c   REVOKE ALL ON SCHEMA audit FROM postgres;
GRANT ALL ON SCHEMA audit TO postgres WITH GRANT OPTION;
                  postgres    false    6                        2615    67234    general    SCHEMA        CREATE SCHEMA general;
    DROP SCHEMA general;
             postgres    false            	           0    0    general    ACL     g   REVOKE ALL ON SCHEMA general FROM postgres;
GRANT ALL ON SCHEMA general TO postgres WITH GRANT OPTION;
                  postgres    false    5                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3            	            2615    67231    security    SCHEMA        CREATE SCHEMA security;
    DROP SCHEMA security;
             postgres    false            	           0    0    SCHEMA security    COMMENT     Z   COMMENT ON SCHEMA security IS 'Es el esquema que maneja la seguridad de la aplicacioón';
                  postgres    false    9            	           0    0    security    ACL     i   REVOKE ALL ON SCHEMA security FROM postgres;
GRANT ALL ON SCHEMA security TO postgres WITH GRANT OPTION;
                  postgres    false    9                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    116767 	   parameter    TABLE     l   CREATE TABLE parameter (
    id_parameter integer NOT NULL,
    num_favorites integer DEFAULT 5 NOT NULL
);
    DROP TABLE general.parameter;
       general         postgres    false    5            	           0    0    COLUMN parameter.id_parameter    COMMENT     M   COMMENT ON COLUMN parameter.id_parameter IS 'Es llave primaria de la tabla';
            general       postgres    false    215            	           0    0    COLUMN parameter.num_favorites    COMMENT     �   COMMENT ON COLUMN parameter.num_favorites IS 'Es el campo para saber cuantos favoritos puede almacenar un usuario del sistema';
            general       postgres    false    215            �            1259    116765    parameters_id_seq    SEQUENCE     s   CREATE SEQUENCE parameters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE general.parameters_id_seq;
       general       postgres    false    215    5            	           0    0    parameters_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE parameters_id_seq OWNED BY parameter.id_parameter;
            general       postgres    false    214            �            1259    83944    person    TABLE        CREATE TABLE person (
    id_person bigint NOT NULL,
    birthdate timestamp without time zone,
    first_name character varying(100) NOT NULL,
    identification integer NOT NULL,
    last_name character varying(100) NOT NULL,
    registered timestamp without time zone DEFAULT now()
);
    DROP TABLE general.person;
       general         postgres    false    5            	           0    0    COLUMN person.id_person    COMMENT     G   COMMENT ON COLUMN person.id_person IS 'Es llave primaria de la tabla';
            general       postgres    false    205            �            1259    83942    person_id_person_seq    SEQUENCE     v   CREATE SEQUENCE person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE general.person_id_person_seq;
       general       postgres    false    205    5            	           0    0    person_id_person_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE person_id_person_seq OWNED BY person.id_person;
            general       postgres    false    204            �            1259    75492    action    TABLE     �   CREATE TABLE action (
    id_action integer DEFAULT nextval(('security.action_id_action_seq'::text)::regclass) NOT NULL,
    action character varying(20) NOT NULL
);
    DROP TABLE security.action;
       security         postgres    false    9            �            1259    83963    action_id_action_seq    SEQUENCE     ~   CREATE SEQUENCE action_id_action_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 -   DROP SEQUENCE security.action_id_action_seq;
       security       postgres    false    9            �            1259    92044 	   authority    TABLE     b   CREATE TABLE authority (
    id_auth integer NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE security.authority;
       security         postgres    false    9            �            1259    92042    authority_id_auth_seq    SEQUENCE     w   CREATE SEQUENCE authority_id_auth_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE security.authority_id_auth_seq;
       security       postgres    false    9    211            	           0    0    authority_id_auth_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE authority_id_auth_seq OWNED BY authority.id_auth;
            security       postgres    false    210            �            1259    75416    level    TABLE     �   CREATE TABLE level (
    id_level integer NOT NULL,
    description character varying(100) NOT NULL,
    name character varying(50) NOT NULL,
    level_pos numeric(2,0) NOT NULL,
    is_resource boolean DEFAULT false NOT NULL
);
    DROP TABLE security.level;
       security         postgres    false    9            �            1259    75414    level_id_level_seq    SEQUENCE     t   CREATE SEQUENCE level_id_level_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE security.level_id_level_seq;
       security       postgres    false    189    9            	           0    0    level_id_level_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE level_id_level_seq OWNED BY level.id_level;
            security       postgres    false    188            �            1259    75478    option    TABLE     �   CREATE TABLE option (
    id_option integer NOT NULL,
    id_level integer NOT NULL,
    detail character varying(300) NOT NULL,
    id_option_father integer,
    name character varying(50) NOT NULL,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE security.option;
       security         postgres    false    9            	           0    0    COLUMN option.id_option_father    COMMENT     q   COMMENT ON COLUMN option.id_option_father IS 'es el id que hereda del padre, si no tiene padre va null o vacio';
            security       postgres    false    193            �            1259    75486    option_action    TABLE     �   CREATE TABLE option_action (
    id_option_action integer NOT NULL,
    id_option integer NOT NULL,
    id_action integer NOT NULL
);
 #   DROP TABLE security.option_action;
       security         postgres    false    9            �            1259    75484 "   option_action_id_option_action_seq    SEQUENCE     �   CREATE SEQUENCE option_action_id_option_action_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE security.option_action_id_option_action_seq;
       security       postgres    false    195    9            	           0    0 "   option_action_id_option_action_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE option_action_id_option_action_seq OWNED BY option_action.id_option_action;
            security       postgres    false    194            �            1259    116776    option_favorite    TABLE     o   CREATE TABLE option_favorite (
    id_option_favorite integer NOT NULL,
    id_user_option integer NOT NULL
);
 %   DROP TABLE security.option_favorite;
       security         postgres    false    9             	           0    0 )   COLUMN option_favorite.id_option_favorite    COMMENT     \   COMMENT ON COLUMN option_favorite.id_option_favorite IS 'Es la llave primaria de la tabla';
            security       postgres    false    217            !	           0    0 %   COLUMN option_favorite.id_user_option    COMMENT     c   COMMENT ON COLUMN option_favorite.id_user_option IS 'Es la llave foranea de la tabla user_option';
            security       postgres    false    217            �            1259    116774 &   option_favorite_id_option_favorite_seq    SEQUENCE     �   CREATE SEQUENCE option_favorite_id_option_favorite_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE security.option_favorite_id_option_favorite_seq;
       security       postgres    false    9    217            "	           0    0 &   option_favorite_id_option_favorite_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE option_favorite_id_option_favorite_seq OWNED BY option_favorite.id_option_favorite;
            security       postgres    false    216            �            1259    75476    option_id_option_seq    SEQUENCE     v   CREATE SEQUENCE option_id_option_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE security.option_id_option_seq;
       security       postgres    false    193    9            #	           0    0    option_id_option_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE option_id_option_seq OWNED BY option.id_option;
            security       postgres    false    192            �            1259    75532    profile_option_helper    TABLE     �   CREATE TABLE profile_option_helper (
    id_p_o_helper integer NOT NULL,
    id_profile_helper integer NOT NULL,
    id_option integer NOT NULL
);
 +   DROP TABLE security.profile_option_helper;
       security         postgres    false    9            �            1259    75530 &   profil_option_helper_id_p_o_helper_seq    SEQUENCE     �   CREATE SEQUENCE profil_option_helper_id_p_o_helper_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE security.profil_option_helper_id_p_o_helper_seq;
       security       postgres    false    9    203            $	           0    0 &   profil_option_helper_id_p_o_helper_seq    SEQUENCE OWNED BY     d   ALTER SEQUENCE profil_option_helper_id_p_o_helper_seq OWNED BY profile_option_helper.id_p_o_helper;
            security       postgres    false    202            �            1259    75522    profile_helper    TABLE     t   CREATE TABLE profile_helper (
    id_profile_helper integer NOT NULL,
    profile character varying(20) NOT NULL
);
 $   DROP TABLE security.profile_helper;
       security         postgres    false    9            �            1259    75520 $   profile_helper_id_profile_helper_seq    SEQUENCE     �   CREATE SEQUENCE profile_helper_id_profile_helper_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE security.profile_helper_id_profile_helper_seq;
       security       postgres    false    9    201            %	           0    0 $   profile_helper_id_profile_helper_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE profile_helper_id_profile_helper_seq OWNED BY profile_helper.id_profile_helper;
            security       postgres    false    200            �            1259    75514    user_access_history    TABLE     �   CREATE TABLE user_access_history (
    id_history integer NOT NULL,
    id_user integer NOT NULL,
    date_access timestamp(0) without time zone NOT NULL,
    ip_access character varying(20),
    detail character varying(200)
);
 )   DROP TABLE security.user_access_history;
       security         postgres    false    9            �            1259    75512 "   user_access_history_id_history_seq    SEQUENCE     �   CREATE SEQUENCE user_access_history_id_history_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE security.user_access_history_id_history_seq;
       security       postgres    false    199    9            &	           0    0 "   user_access_history_id_history_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE user_access_history_id_history_seq OWNED BY user_access_history.id_history;
            security       postgres    false    198            �            1259    92054    user_authority    TABLE        CREATE TABLE user_authority (
    id_user_auth integer NOT NULL,
    id_user integer NOT NULL,
    id_auth integer NOT NULL
);
 $   DROP TABLE security.user_authority;
       security         postgres    false    9            �            1259    92052    user_authority_id_user_auth_seq    SEQUENCE     �   CREATE SEQUENCE user_authority_id_user_auth_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE security.user_authority_id_user_auth_seq;
       security       postgres    false    9    213            '	           0    0    user_authority_id_user_auth_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE user_authority_id_user_auth_seq OWNED BY user_authority.id_user_auth;
            security       postgres    false    212            �            1259    75499    user_option    TABLE     �   CREATE TABLE user_option (
    id_user_option integer DEFAULT nextval(('security.user_option_id_user_option_seq'::text)::regclass) NOT NULL,
    id_user integer NOT NULL,
    id_option integer NOT NULL
);
 !   DROP TABLE security.user_option;
       security         postgres    false    9            �            1259    92026    user_option_action    TABLE     �   CREATE TABLE user_option_action (
    id_user_option_action integer NOT NULL,
    id_user_option integer NOT NULL,
    id_option_action integer NOT NULL
);
 (   DROP TABLE security.user_option_action;
       security         postgres    false    9            �            1259    92024 ,   user_option_action_id_user_option_action_seq    SEQUENCE     �   CREATE SEQUENCE user_option_action_id_user_option_action_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE security.user_option_action_id_user_option_action_seq;
       security       postgres    false    9    209            (	           0    0 ,   user_option_action_id_user_option_action_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE user_option_action_id_user_option_action_seq OWNED BY user_option_action.id_user_option_action;
            security       postgres    false    208            �            1259    83958    user_option_id_user_option_seq    SEQUENCE     �   CREATE SEQUENCE user_option_id_user_option_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;
 7   DROP SEQUENCE security.user_option_id_user_option_seq;
       security       postgres    false    9            �            1259    75468    users    TABLE     �   CREATE TABLE users (
    id_user integer NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(100) NOT NULL,
    enabled boolean NOT NULL,
    general_id_person integer NOT NULL,
    is_profile boolean DEFAULT false
);
    DROP TABLE security.users;
       security         postgres    false    9            �            1259    75466    users_id_user_seq    SEQUENCE     s   CREATE SEQUENCE users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE security.users_id_user_seq;
       security       postgres    false    9    191            )	           0    0    users_id_user_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE users_id_user_seq OWNED BY users.id_user;
            security       postgres    false    190            9           2604    116770    parameter id_parameter    DEFAULT     i   ALTER TABLE ONLY parameter ALTER COLUMN id_parameter SET DEFAULT nextval('parameters_id_seq'::regclass);
 F   ALTER TABLE general.parameter ALTER COLUMN id_parameter DROP DEFAULT;
       general       postgres    false    215    214    215            4           2604    83947    person id_person    DEFAULT     f   ALTER TABLE ONLY person ALTER COLUMN id_person SET DEFAULT nextval('person_id_person_seq'::regclass);
 @   ALTER TABLE general.person ALTER COLUMN id_person DROP DEFAULT;
       general       postgres    false    205    204    205            7           2604    92047    authority id_auth    DEFAULT     h   ALTER TABLE ONLY authority ALTER COLUMN id_auth SET DEFAULT nextval('authority_id_auth_seq'::regclass);
 B   ALTER TABLE security.authority ALTER COLUMN id_auth DROP DEFAULT;
       security       postgres    false    211    210    211            (           2604    75419    level id_level    DEFAULT     b   ALTER TABLE ONLY level ALTER COLUMN id_level SET DEFAULT nextval('level_id_level_seq'::regclass);
 ?   ALTER TABLE security.level ALTER COLUMN id_level DROP DEFAULT;
       security       postgres    false    189    188    189            ,           2604    75481    option id_option    DEFAULT     f   ALTER TABLE ONLY option ALTER COLUMN id_option SET DEFAULT nextval('option_id_option_seq'::regclass);
 A   ALTER TABLE security.option ALTER COLUMN id_option DROP DEFAULT;
       security       postgres    false    192    193    193            .           2604    75489    option_action id_option_action    DEFAULT     �   ALTER TABLE ONLY option_action ALTER COLUMN id_option_action SET DEFAULT nextval('option_action_id_option_action_seq'::regclass);
 O   ALTER TABLE security.option_action ALTER COLUMN id_option_action DROP DEFAULT;
       security       postgres    false    194    195    195            ;           2604    116779 "   option_favorite id_option_favorite    DEFAULT     �   ALTER TABLE ONLY option_favorite ALTER COLUMN id_option_favorite SET DEFAULT nextval('option_favorite_id_option_favorite_seq'::regclass);
 S   ALTER TABLE security.option_favorite ALTER COLUMN id_option_favorite DROP DEFAULT;
       security       postgres    false    216    217    217            2           2604    75525     profile_helper id_profile_helper    DEFAULT     �   ALTER TABLE ONLY profile_helper ALTER COLUMN id_profile_helper SET DEFAULT nextval('profile_helper_id_profile_helper_seq'::regclass);
 Q   ALTER TABLE security.profile_helper ALTER COLUMN id_profile_helper DROP DEFAULT;
       security       postgres    false    201    200    201            3           2604    75535 #   profile_option_helper id_p_o_helper    DEFAULT     �   ALTER TABLE ONLY profile_option_helper ALTER COLUMN id_p_o_helper SET DEFAULT nextval('profil_option_helper_id_p_o_helper_seq'::regclass);
 T   ALTER TABLE security.profile_option_helper ALTER COLUMN id_p_o_helper DROP DEFAULT;
       security       postgres    false    203    202    203            1           2604    75517    user_access_history id_history    DEFAULT     �   ALTER TABLE ONLY user_access_history ALTER COLUMN id_history SET DEFAULT nextval('user_access_history_id_history_seq'::regclass);
 O   ALTER TABLE security.user_access_history ALTER COLUMN id_history DROP DEFAULT;
       security       postgres    false    198    199    199            8           2604    92057    user_authority id_user_auth    DEFAULT     |   ALTER TABLE ONLY user_authority ALTER COLUMN id_user_auth SET DEFAULT nextval('user_authority_id_user_auth_seq'::regclass);
 L   ALTER TABLE security.user_authority ALTER COLUMN id_user_auth DROP DEFAULT;
       security       postgres    false    212    213    213            6           2604    92029 (   user_option_action id_user_option_action    DEFAULT     �   ALTER TABLE ONLY user_option_action ALTER COLUMN id_user_option_action SET DEFAULT nextval('user_option_action_id_user_option_action_seq'::regclass);
 Y   ALTER TABLE security.user_option_action ALTER COLUMN id_user_option_action DROP DEFAULT;
       security       postgres    false    208    209    209            *           2604    75471    users id_user    DEFAULT     `   ALTER TABLE ONLY users ALTER COLUMN id_user SET DEFAULT nextval('users_id_user_seq'::regclass);
 >   ALTER TABLE security.users ALTER COLUMN id_user DROP DEFAULT;
       security       postgres    false    191    190    191            		          0    116767 	   parameter 
   TABLE DATA               9   COPY parameter (id_parameter, num_favorites) FROM stdin;
    general       postgres    false    215   ˨       *	           0    0    parameters_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('parameters_id_seq', 3, true);
            general       postgres    false    214            �          0    83944    person 
   TABLE DATA               b   COPY person (id_person, birthdate, first_name, identification, last_name, registered) FROM stdin;
    general       postgres    false    205   �       +	           0    0    person_id_person_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('person_id_person_seq', 5, true);
            general       postgres    false    204            �          0    75492    action 
   TABLE DATA               ,   COPY action (id_action, action) FROM stdin;
    security       postgres    false    196   j�       ,	           0    0    action_id_action_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('action_id_action_seq', 28, true);
            security       postgres    false    207            	          0    92044 	   authority 
   TABLE DATA               +   COPY authority (id_auth, name) FROM stdin;
    security       postgres    false    211   ��       -	           0    0    authority_id_auth_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('authority_id_auth_seq', 3, true);
            security       postgres    false    210            �          0    75416    level 
   TABLE DATA               M   COPY level (id_level, description, name, level_pos, is_resource) FROM stdin;
    security       postgres    false    189   �       .	           0    0    level_id_level_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('level_id_level_seq', 5, true);
            security       postgres    false    188            �          0    75478    option 
   TABLE DATA               V   COPY option (id_option, id_level, detail, id_option_father, name, active) FROM stdin;
    security       postgres    false    193   V�       �          0    75486    option_action 
   TABLE DATA               H   COPY option_action (id_option_action, id_option, id_action) FROM stdin;
    security       postgres    false    195   �       /	           0    0 "   option_action_id_option_action_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('option_action_id_option_action_seq', 65, true);
            security       postgres    false    194            	          0    116776    option_favorite 
   TABLE DATA               F   COPY option_favorite (id_option_favorite, id_user_option) FROM stdin;
    security       postgres    false    217   ��       0	           0    0 &   option_favorite_id_option_favorite_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('option_favorite_id_option_favorite_seq', 1, true);
            security       postgres    false    216            1	           0    0    option_id_option_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('option_id_option_seq', 59, true);
            security       postgres    false    192            2	           0    0 &   profil_option_helper_id_p_o_helper_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('profil_option_helper_id_p_o_helper_seq', 1, true);
            security       postgres    false    202            �          0    75522    profile_helper 
   TABLE DATA               =   COPY profile_helper (id_profile_helper, profile) FROM stdin;
    security       postgres    false    201   Ҭ       3	           0    0 $   profile_helper_id_profile_helper_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('profile_helper_id_profile_helper_seq', 2, true);
            security       postgres    false    200            �          0    75532    profile_option_helper 
   TABLE DATA               U   COPY profile_option_helper (id_p_o_helper, id_profile_helper, id_option) FROM stdin;
    security       postgres    false    203   �       �          0    75514    user_access_history 
   TABLE DATA               [   COPY user_access_history (id_history, id_user, date_access, ip_access, detail) FROM stdin;
    security       postgres    false    199   *�       4	           0    0 "   user_access_history_id_history_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('user_access_history_id_history_seq', 1, false);
            security       postgres    false    198            	          0    92054    user_authority 
   TABLE DATA               A   COPY user_authority (id_user_auth, id_user, id_auth) FROM stdin;
    security       postgres    false    213   G�       5	           0    0    user_authority_id_user_auth_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('user_authority_id_user_auth_seq', 25, true);
            security       postgres    false    212            �          0    75499    user_option 
   TABLE DATA               B   COPY user_option (id_user_option, id_user, id_option) FROM stdin;
    security       postgres    false    197   t�       	          0    92026    user_option_action 
   TABLE DATA               ^   COPY user_option_action (id_user_option_action, id_user_option, id_option_action) FROM stdin;
    security       postgres    false    209   ,�       6	           0    0 ,   user_option_action_id_user_option_action_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('user_option_action_id_user_option_action_seq', 179, true);
            security       postgres    false    208            7	           0    0    user_option_id_user_option_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('user_option_id_user_option_seq', 278, true);
            security       postgres    false    206            �          0    75468    users 
   TABLE DATA               ]   COPY users (id_user, username, password, enabled, general_id_person, is_profile) FROM stdin;
    security       postgres    false    191   p�       8	           0    0    users_id_user_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('users_id_user_seq', 3, true);
            security       postgres    false    190            g           2606    116773    parameter parameters_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY parameter
    ADD CONSTRAINT parameters_pkey PRIMARY KEY (id_parameter);
 D   ALTER TABLE ONLY general.parameter DROP CONSTRAINT parameters_pkey;
       general         postgres    false    215    215            [           2606    83949    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);
 =   ALTER TABLE ONLY general.person DROP CONSTRAINT person_pkey;
       general         postgres    false    205    205            ]           2606    83951 #   person uk_4r2cs4eybw7joyi0u8v7vywhg 
   CONSTRAINT     a   ALTER TABLE ONLY person
    ADD CONSTRAINT uk_4r2cs4eybw7joyi0u8v7vywhg UNIQUE (identification);
 N   ALTER TABLE ONLY general.person DROP CONSTRAINT uk_4r2cs4eybw7joyi0u8v7vywhg;
       general         postgres    false    205    205            L           2606    75498    action action_action_key 
   CONSTRAINT     N   ALTER TABLE ONLY action
    ADD CONSTRAINT action_action_key UNIQUE (action);
 D   ALTER TABLE ONLY security.action DROP CONSTRAINT action_action_key;
       security         postgres    false    196    196            N           2606    83966    action action_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY action
    ADD CONSTRAINT action_pkey PRIMARY KEY (id_action);
 >   ALTER TABLE ONLY security.action DROP CONSTRAINT action_pkey;
       security         postgres    false    196    196            a           2606    92051    authority authority_name_key 
   CONSTRAINT     P   ALTER TABLE ONLY authority
    ADD CONSTRAINT authority_name_key UNIQUE (name);
 H   ALTER TABLE ONLY security.authority DROP CONSTRAINT authority_name_key;
       security         postgres    false    211    211            c           2606    92049    authority authority_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id_auth);
 D   ALTER TABLE ONLY security.authority DROP CONSTRAINT authority_pkey;
       security         postgres    false    211    211            =           2606    83976    level level_level_key 
   CONSTRAINT     I   ALTER TABLE ONLY level
    ADD CONSTRAINT level_level_key UNIQUE (name);
 A   ALTER TABLE ONLY security.level DROP CONSTRAINT level_level_key;
       security         postgres    false    189    189            ?           2606    75421    level level_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY level
    ADD CONSTRAINT level_pkey PRIMARY KEY (id_level);
 <   ALTER TABLE ONLY security.level DROP CONSTRAINT level_pkey;
       security         postgres    false    189    189            A           2606    83978    level level_position_key 
   CONSTRAINT     Q   ALTER TABLE ONLY level
    ADD CONSTRAINT level_position_key UNIQUE (level_pos);
 D   ALTER TABLE ONLY security.level DROP CONSTRAINT level_position_key;
       security         postgres    false    189    189            J           2606    75491     option_action option_action_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY option_action
    ADD CONSTRAINT option_action_pkey PRIMARY KEY (id_option_action);
 L   ALTER TABLE ONLY security.option_action DROP CONSTRAINT option_action_pkey;
       security         postgres    false    195    195            i           2606    116781 $   option_favorite option_favorite_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY option_favorite
    ADD CONSTRAINT option_favorite_pkey PRIMARY KEY (id_option_favorite);
 P   ALTER TABLE ONLY security.option_favorite DROP CONSTRAINT option_favorite_pkey;
       security         postgres    false    217    217            H           2606    75483    option option_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY option
    ADD CONSTRAINT option_pkey PRIMARY KEY (id_option);
 >   ALTER TABLE ONLY security.option DROP CONSTRAINT option_pkey;
       security         postgres    false    193    193            Y           2606    75537 /   profile_option_helper profil_option_helper_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY profile_option_helper
    ADD CONSTRAINT profil_option_helper_pkey PRIMARY KEY (id_p_o_helper);
 [   ALTER TABLE ONLY security.profile_option_helper DROP CONSTRAINT profil_option_helper_pkey;
       security         postgres    false    203    203            U           2606    75527 "   profile_helper profile_helper_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY profile_helper
    ADD CONSTRAINT profile_helper_pkey PRIMARY KEY (id_profile_helper);
 N   ALTER TABLE ONLY security.profile_helper DROP CONSTRAINT profile_helper_pkey;
       security         postgres    false    201    201            W           2606    75529 )   profile_helper profile_helper_profile_key 
   CONSTRAINT     `   ALTER TABLE ONLY profile_helper
    ADD CONSTRAINT profile_helper_profile_key UNIQUE (profile);
 U   ALTER TABLE ONLY security.profile_helper DROP CONSTRAINT profile_helper_profile_key;
       security         postgres    false    201    201            S           2606    75519 ,   user_access_history user_access_history_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY user_access_history
    ADD CONSTRAINT user_access_history_pkey PRIMARY KEY (id_history);
 X   ALTER TABLE ONLY security.user_access_history DROP CONSTRAINT user_access_history_pkey;
       security         postgres    false    199    199            e           2606    92059 "   user_authority user_authority_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY user_authority
    ADD CONSTRAINT user_authority_pkey PRIMARY KEY (id_user_auth);
 N   ALTER TABLE ONLY security.user_authority DROP CONSTRAINT user_authority_pkey;
       security         postgres    false    213    213            _           2606    92031 *   user_option_action user_option_action_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY user_option_action
    ADD CONSTRAINT user_option_action_pkey PRIMARY KEY (id_user_option_action);
 V   ALTER TABLE ONLY security.user_option_action DROP CONSTRAINT user_option_action_pkey;
       security         postgres    false    209    209            P           2606    83961    user_option user_option_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY user_option
    ADD CONSTRAINT user_option_pkey PRIMARY KEY (id_user_option);
 H   ALTER TABLE ONLY security.user_option DROP CONSTRAINT user_option_pkey;
       security         postgres    false    197    197            D           2606    75473    users users_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);
 <   ALTER TABLE ONLY security.users DROP CONSTRAINT users_pkey;
       security         postgres    false    191    191            F           2606    75475    users users_username_key 
   CONSTRAINT     P   ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 D   ALTER TABLE ONLY security.users DROP CONSTRAINT users_username_key;
       security         postgres    false    191    191            B           1259    75549    fki_fk_id_person    INDEX     H   CREATE INDEX fki_fk_id_person ON users USING btree (general_id_person);
 &   DROP INDEX security.fki_fk_id_person;
       security         postgres    false    191            Q           1259    75543    fki_fk_id_user    INDEX     J   CREATE INDEX fki_fk_id_user ON user_access_history USING btree (id_user);
 $   DROP INDEX security.fki_fk_id_user;
       security         postgres    false    199            q           2606    75538    user_access_history fk_id_user    FK CONSTRAINT     t   ALTER TABLE ONLY user_access_history
    ADD CONSTRAINT fk_id_user FOREIGN KEY (id_user) REFERENCES users(id_user);
 J   ALTER TABLE ONLY security.user_access_history DROP CONSTRAINT fk_id_user;
       security       postgres    false    191    2116    199            9	           0    0 ,   CONSTRAINT fk_id_user ON user_access_history    COMMENT     ^   COMMENT ON CONSTRAINT fk_id_user ON user_access_history IS 'es la foranea de la tabla users';
            security       postgres    false    2161            j           2606    83953 !   users fk_yy3lv20yyqvdklhxjemj9g7h    FK CONSTRAINT     �   ALTER TABLE ONLY users
    ADD CONSTRAINT fk_yy3lv20yyqvdklhxjemj9g7h FOREIGN KEY (general_id_person) REFERENCES general.person(id_person);
 M   ALTER TABLE ONLY security.users DROP CONSTRAINT fk_yy3lv20yyqvdklhxjemj9g7h;
       security       postgres    false    191    2139    205            n           2606    116616    option_action option_action_fk    FK CONSTRAINT     �   ALTER TABLE ONLY option_action
    ADD CONSTRAINT option_action_fk FOREIGN KEY (id_option) REFERENCES option(id_option) ON DELETE CASCADE;
 J   ALTER TABLE ONLY security.option_action DROP CONSTRAINT option_action_fk;
       security       postgres    false    195    2120    193            m           2606    83967    option_action option_action_fk1    FK CONSTRAINT     z   ALTER TABLE ONLY option_action
    ADD CONSTRAINT option_action_fk1 FOREIGN KEY (id_action) REFERENCES action(id_action);
 K   ALTER TABLE ONLY security.option_action DROP CONSTRAINT option_action_fk1;
       security       postgres    false    195    2126    196            x           2606    116787 "   option_favorite option_favorite_fk    FK CONSTRAINT     �   ALTER TABLE ONLY option_favorite
    ADD CONSTRAINT option_favorite_fk FOREIGN KEY (id_user_option) REFERENCES user_option(id_user_option) ON DELETE CASCADE;
 N   ALTER TABLE ONLY security.option_favorite DROP CONSTRAINT option_favorite_fk;
       security       postgres    false    217    197    2128            :	           0    0 0   CONSTRAINT option_favorite_fk ON option_favorite    COMMENT     u   COMMENT ON CONSTRAINT option_favorite_fk ON option_favorite IS 'Es la relacion entre option_favorite y user_option';
            security       postgres    false    2168            k           2606    75604    option option_fk    FK CONSTRAINT     h   ALTER TABLE ONLY option
    ADD CONSTRAINT option_fk FOREIGN KEY (id_level) REFERENCES level(id_level);
 <   ALTER TABLE ONLY security.option DROP CONSTRAINT option_fk;
       security       postgres    false    189    2111    193            l           2606    116621    option option_fk1    FK CONSTRAINT     �   ALTER TABLE ONLY option
    ADD CONSTRAINT option_fk1 FOREIGN KEY (id_option_father) REFERENCES option(id_option) ON DELETE CASCADE;
 =   ALTER TABLE ONLY security.option DROP CONSTRAINT option_fk1;
       security       postgres    false    2120    193    193            r           2606    75578 -   profile_option_helper profil_option_helper_fk    FK CONSTRAINT     �   ALTER TABLE ONLY profile_option_helper
    ADD CONSTRAINT profil_option_helper_fk FOREIGN KEY (id_profile_helper) REFERENCES profile_helper(id_profile_helper);
 Y   ALTER TABLE ONLY security.profile_option_helper DROP CONSTRAINT profil_option_helper_fk;
       security       postgres    false    2133    203    201            s           2606    75589 .   profile_option_helper profil_option_helper_fk1    FK CONSTRAINT     �   ALTER TABLE ONLY profile_option_helper
    ADD CONSTRAINT profil_option_helper_fk1 FOREIGN KEY (id_option) REFERENCES option(id_option);
 Z   ALTER TABLE ONLY security.profile_option_helper DROP CONSTRAINT profil_option_helper_fk1;
       security       postgres    false    203    2120    193            v           2606    92060     user_authority user_authority_fk    FK CONSTRAINT     v   ALTER TABLE ONLY user_authority
    ADD CONSTRAINT user_authority_fk FOREIGN KEY (id_user) REFERENCES users(id_user);
 L   ALTER TABLE ONLY security.user_authority DROP CONSTRAINT user_authority_fk;
       security       postgres    false    2116    191    213            w           2606    92065 !   user_authority user_authority_fk1    FK CONSTRAINT     {   ALTER TABLE ONLY user_authority
    ADD CONSTRAINT user_authority_fk1 FOREIGN KEY (id_auth) REFERENCES authority(id_auth);
 M   ALTER TABLE ONLY security.user_authority DROP CONSTRAINT user_authority_fk1;
       security       postgres    false    2147    211    213            t           2606    116601 (   user_option_action user_option_action_fk    FK CONSTRAINT     �   ALTER TABLE ONLY user_option_action
    ADD CONSTRAINT user_option_action_fk FOREIGN KEY (id_user_option) REFERENCES user_option(id_user_option) ON DELETE CASCADE;
 T   ALTER TABLE ONLY security.user_option_action DROP CONSTRAINT user_option_action_fk;
       security       postgres    false    2128    209    197            u           2606    116606 )   user_option_action user_option_action_fk1    FK CONSTRAINT     �   ALTER TABLE ONLY user_option_action
    ADD CONSTRAINT user_option_action_fk1 FOREIGN KEY (id_option_action) REFERENCES option_action(id_option_action) ON DELETE CASCADE;
 U   ALTER TABLE ONLY security.user_option_action DROP CONSTRAINT user_option_action_fk1;
       security       postgres    false    195    209    2122            o           2606    75550    user_option user_option_fk    FK CONSTRAINT     p   ALTER TABLE ONLY user_option
    ADD CONSTRAINT user_option_fk FOREIGN KEY (id_user) REFERENCES users(id_user);
 F   ALTER TABLE ONLY security.user_option DROP CONSTRAINT user_option_fk;
       security       postgres    false    2116    191    197            p           2606    75555    user_option user_option_fk1    FK CONSTRAINT     v   ALTER TABLE ONLY user_option
    ADD CONSTRAINT user_option_fk1 FOREIGN KEY (id_option) REFERENCES option(id_option);
 G   ALTER TABLE ONLY security.user_option DROP CONSTRAINT user_option_fk1;
       security       postgres    false    2120    193    197            		      x�3�4����� �$      �   n   x�3���LN,��/�4422� ��ļ����*N#Cs]K]#3C+CS+K.#��ļ��T�&d�1~\� I�P�0G� נ`?�S3sKN� WO��=... >L�      �   7   x�3�tru�2�t�����2�8]]<C�N��`g����k������� >�n      	   /   x�3�tt����	r��2�tu���9]}|�#]]�b���� ��	�      �   V   x�3���,K�Q0�JM.-*��4�,�2���p:'��$r�p�qBE9r2��3o��4�Ae�8}�SJs�9���1z\\\ ?:�      �   �  x�eS�R�0<+_�/�`'��1��L����@f��c;��#9v��^jI���Y �LS[m!�'}莚�ČK�88e����5Gx{��j��J�!���X-BU1C��WҜ���<�r�ڕTɱ���Ƨ���qM�)d���(���j��l�l�	��(�?`�����u;��1k;��1l-I���K9\9Ÿd�QQ�$"RX�	&!�⻶C[^)dg�t��~Rv�BVMQs�(��*�7�9Z�r�q�+,�X �C�Ǝ�;ك6��n�$]��6*A@�S���v��S��c�D��U3,���q�O�*#����-��O�q�(!�ez���͛f��S��`�l~5���.�<4u�9#[	����\�N/��rnNu���y�Y��;���x�]�<6�R-M+��%9���L}�*m��%o�$I� ��,�      �   �   x����0C�3*f���^�+���'��m"�r[�&��}m[�
V���Ԃi�(� -�����V�
_K���A�~���B�Mh+����v���c���u���W�-L���*�;|? �K&      	      x�3�4����� r!      �   %   x�3�tL����,.)JL�/�2�t,M�,�b���� �	[      �      x�3�4�4����� �X      �      x������ � �      	      x�3�4�4�2�F\F��F@N� %^�      �   �   x��˕�0Bס�9�>V��뿎�zc�A ���|j�G��z����I�Q�x�m��~�,���I�T�|����~����Û0�3��=�Y��a�F^R�M���r{L\s�'L�e����~��䣺�K����>>��*��>�bT�%m���`q����$�y:0�      	   4   x�%��	 0����������h���y�ђ�.������T�t�wZx��
�      �   5   x�3�,(�O��I-�LL����L�4�,�2�� d	P,�˘�$��E(F��� �M     