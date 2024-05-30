-- Inserimenti dei cuochi
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, immagine) VALUES(nextval('cuoco_seq'), 'Bruno', 'Barbieri', '1962-01-12', '/images/cuochi/Bruno-Barbieri.jpg');
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, immagine) VALUES(nextval('cuoco_seq'), 'Leonardo', 'Crozzoli', '2003-03-26', '/images/cuochi/Leonardo-Crozzoli.jpg');
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, immagine) VALUES(nextval('cuoco_seq'), 'Lorenzo', 'Benzi', '2002-01-16', '/images/cuochi/Lorenzo-Benzi.jpg');

-- Inserimenti delle ricette
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, immagine) VALUES(nextval('ricetta_seq'), 'Cacio e Pepe', 'La nostra cacio e pepe è un omaggio alla tradizione culinaria romana, un piatto che incarna l essenza della cucina italiana con pochi e semplici ingredienti', (SELECT id FROM cuoco WHERE nome = 'Lorenzo' AND cognome = 'Benzi'), '/images/ricette/CacioePepe.jpg');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, immagine) VALUES(nextval('ricetta_seq'), 'Lasagna', 'La nostra deliziosa lasagna è un tripudio di sapori italiani che conquista il palato ad ogni morso.', (SELECT id FROM cuoco WHERE nome = 'Bruno' AND cognome = 'Barbieri'), '/images/ricette/Lasagna.jpeg');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, immagine) VALUES(nextval('ricetta_seq'), 'Tiramisù', 'Il nostro tiramisù è un autentico capolavoro della pasticceria italiana, un dolce che incanta i palati di chiunque lo assaggi.', (SELECT id FROM cuoco WHERE nome = 'Leonardo' AND cognome = 'Crozzoli'), '/images/ricette/Tiramisu.jpg');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, immagine) VALUES(nextval('ricetta_seq'), 'Risotto ai Funghi', 'Il nostro risotto ai funghi è una delizia culinaria che unisce la cremosità del riso Carnaroli con il gusto intenso dei funghi porcini.', (SELECT id FROM cuoco WHERE nome = 'Bruno' AND cognome = 'Barbieri'), '/images/ricette/RisottoaiFunghi.jpg');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, immagine) VALUES(nextval('ricetta_seq'), 'Spaghetti Carbonara', 'La nostra versione della classica carbonara, che regala un viaggio sensoriale unico e appagante.', (SELECT id FROM cuoco WHERE nome = 'Leonardo' AND cognome = 'Crozzoli'), '/images/ricette/Carbonara.jpg');

-- Inserimenti degli ingredienti
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pomodoro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Olio extravergine d oliva');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Parmigiano Reggiano');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Melanzane');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Ricotta Salata');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Riso');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Mozzarella');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Piselli');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Panna');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pesce');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Cozze');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Vongole');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Mandorle');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Cioccolato');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Guanciale');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pecorino Romano');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Uova');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pepe Nero');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pasta');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Farina');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Burro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Latte');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Carne Macinata');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Salsa di Pomodoro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Besciamella');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Savoiardi');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Caffè');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Mascarpone');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Cacao Amaro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Riso Carnaroli');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Brodo Vegetale');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Funghi Porcini');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Aglio');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Prezzemolo');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Formaggio Gorgonzola');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Noci');

-- Inserimenti delle righe ricetta
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Olio extravergine d oliva'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '30 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Parmigiano Reggiano'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '50 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Parmigiano Reggiano'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '40 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pecorino Romano'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), '100 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pepe Nero'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '300 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Carne Macinata'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '400 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Salsa di Pomodoro'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '500 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Besciamella'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '300 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Mozzarella'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Savoiardi'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Caffè'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '200 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Mascarpone'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '500 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Cacao Amaro'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Riso Carnaroli'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '300 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Brodo Vegetale'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '1 l');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Funghi Porcini'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Aglio'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '2 spicchi');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Prezzemolo'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Guanciale'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '150 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pecorino Romano'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '100 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Uova'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '3');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pepe Nero'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '200 gr');