-- Inserimento dei dati per Leonardo Crozzoli
INSERT INTO utente (nome, cognome, email, data_di_nascita) 
VALUES ('Leonardo', 'Crozzoli', 'leo.crozzoli@stud.uniroma3.it', '2003-03-26');

INSERT INTO credenziali (username, password, ruolo, utente_id) 
VALUES ('crozz', '$2a$10$GJm/2jKxYOwC3b6sCFlAWOUxk8YVf8yA0ZUJ8EkL/ig4J55sQoa8C', 'ADMIN_ROLE', 
        (SELECT id FROM utente WHERE email='leo.crozzoli@stud.uniroma3.it'));

INSERT INTO cuoco (nome, cognome, data_di_nascita, immagine, descrizione, utente_id) 
VALUES ('Leonardo', 'Crozzoli', '2003-03-26', 'uploads/leoCuoco', 'lo chef di tormarancia', 
        (SELECT id FROM utente WHERE email='leo.crozzoli@stud.uniroma3.it'));

-- Inserimento dei dati per Lorenzo Benzi
INSERT INTO utente (nome, cognome, email, data_di_nascita) 
VALUES ('Lorenzo', 'Benzi', 'lor.benzi@stud.uniroma3.it', '2002-01-16');

INSERT INTO credenziali (username, password, ruolo, utente_id) 
VALUES ('lollo', '$2a$10$GJm/2jKxYOwC3b6sCFlAWOUxk8YVf8yA0ZUJ8EkL/ig4J55sQoa8C', 'USER_ROLE', 
        (SELECT id FROM utente WHERE email='lor.benzi@stud.uniroma3.it'));

INSERT INTO cuoco (nome, cognome, data_di_nascita, immagine, descrizione, utente_id) 
VALUES ('Lorenzo', 'Benzi', '2002-01-16', 'uploads/lolloCuoco', 'lo chef dell\'infernetto', 
        (SELECT id FROM utente WHERE email='lor.benzi@stud.uniroma3.it'));
