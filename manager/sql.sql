SELECT c.id AS id_client, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.email, c.id_nation, c.id_type, c.id_sexe,
	   co.id AS id_consommation, co.id_service,
       se.description AS seDescription, se.prix, se.etat, 
       tc.description AS tcDescription,
       n.nom_fr_fr
	FROM Client c, Consommation co, Service se, Sexe S, TypeClient tc, Nation n
	WHERE c.id = co.id_client AND co.id_service = se.id 
    						  AND c.id_nation = n.id
                              AND tc.id = c.id_type 
                              AND S.id = c.id_sexe