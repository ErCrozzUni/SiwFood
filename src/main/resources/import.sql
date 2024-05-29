-- Inserimenti dei cuochi
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, url_image) VALUES(nextval('cuoco_id_seq'), 'Bruno', 'Barbieri', '1962-01-12', '/images/cuochi/Bruno-Barbieri.jpg');
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, url_image) VALUES(nextval('cuoco_id_seq'), 'Leonardo', 'Crozzoli', '2003-03-26', '/images/cuochi/Leonardo-Crozzoli.jpg');
INSERT INTO cuoco (id, nome, cognome, data_di_nascita, url_image) VALUES(nextval('cuoco_id_seq'), 'Lorenzo', 'Benzi', '2002-01-16', '/images/cuochi/Lorenzo-Benzi.jpg');

-- Inserimenti delle ricette
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, urls_images) VALUES(nextval('ricetta_id_seq'), 'Cacio e Pepe', 'La nostra cacio e pepe è un omaggio alla tradizione culinaria romana, un piatto che incarna l essenza della cucina italiana con pochi e semplici ingredienti. La pasta, al dente e avvolta in una crema vellutata di pecorino romano e pepe nero macinato al momento, trasporta i vostri sensi direttamente nelle stradine acciottolate di Roma. La combinazione di formaggio stagionato e piccante pepe nero crea un equilibrio di sapori robusti e intensi che si sposano armoniosamente con la pasta, regalando un esperienza gustativa autentica e appagante. Preparata con maestria e amore per la cucina tradizionale, la nostra cacio e pepe vi regalerà un viaggio sensoriale unico, dove ogni forchettata è un esplosione di gusto e nostalgia per i sapori genuini della cucina italiana', (SELECT id FROM cuoco WHERE nome = 'Lorenzo' AND cognome = 'Benzi'), '{/images/ricette/CacioePepe.jpg}');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, urls_images) VALUES(nextval('ricetta_id_seq'), 'Lasagna', 'La nostra deliziosa lasagna è un tripudio di sapori italiani che conquista il palato ad ogni morso. Strati di pasta fresca si alternano con una salsa di pomodoro ricca e saporita, arricchita da carne macinata finemente aromatizzata con spezie mediterranee. Un abbraccio cremoso di besciamella avvolge il tutto, impreziosito da generose porzioni di formaggio mozzarella e parmigiano che si fondono in un mare di filantezza al primo taglio. Gli strati di verdure fresche donano freschezza e consistenza, creando un equilibrio perfetto di gusto e texture. Preparata con amore e passione, la nostra lasagna è un capolavoro culinario che conquisterà il cuore di ogni gourmet e delizierà le tavole di famiglie e amici. Semplice da preparare ma ricca di soddisfazioni, questa lasagna sarà la protagonista indiscussa delle vostre cene memorabili.', (SELECT id FROM cuoco WHERE nome = 'Bruno' AND cognome = 'Barbieri'), '{/images/ricette/Lasagna.jpeg}');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, urls_images) VALUES(nextval('ricetta_id_seq'), 'Tiramisù', 'Il nostro tiramisù è un autentico capolavoro della pasticceria italiana, un dolce che incanta i palati di chiunque lo assaggi. Strati di savoiardi imbevuti nel caffè si alternano con una crema di mascarpone delicata e vellutata, creando un equilibrio perfetto di dolcezza e intensità. Una leggera spolverata di cacao amaro completa questo dessert, regalando un contrasto piacevole di sapori e una nota finale elegante. Preparato con cura e passione, il nostro tiramisù è un omaggio alla tradizione culinaria italiana, capace di trasportare chi lo assapora in un viaggio di piacere e nostalgia. Perfetto da gustare in ogni occasione, questo tiramisù conquisterà il cuore di tutti gli amanti del buon cibo.', (SELECT id FROM cuoco WHERE nome = 'Leonardo' AND cognome = 'Crozzoli'), '{/images/ricette/Tiramisu.jpg}');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, urls_images) VALUES(nextval('ricetta_id_seq'), 'Risotto ai Funghi', 'Il nostro risotto ai funghi è una delizia culinaria che unisce la cremosità del riso Carnaroli con il gusto intenso dei funghi porcini. La base di brodo vegetale, arricchita da una selezione di erbe aromatiche, conferisce al piatto un sapore ricco e avvolgente. I funghi, delicatamente saltati in padella con aglio e prezzemolo, aggiungono profondità e complessità al risotto, creando un equilibrio perfetto di sapori terrosi e cremosità. La mantecatura finale con burro e parmigiano reggiano conferisce al piatto una consistenza vellutata e un sapore avvolgente che conquisterà ogni palato. Preparato con maestria e passione, il nostro risotto ai funghi è un esperienza gastronomica indimenticabile che vi farà innamorare della cucina italiana.', (SELECT id FROM cuoco WHERE nome = 'Bruno' AND cognome = 'Barbieri'), '{/images/ricette/RisottoaiFunghi.jpg}');
INSERT INTO ricetta (id, nome, descrizione, cuoco_id, urls_images) VALUES(nextval('ricetta_id_seq'), 'Spaghetti Carbonara', 'La nostra versione della classica carbonara è un tripudio di sapori italiani che conquista il palato ad ogni forchettata. Spaghetti al dente si sposano con una crema vellutata di uova, pecorino romano e guanciale croccante, creando un equilibrio perfetto di gusto e consistenza. Il tocco finale di pepe nero macinato al momento aggiunge una nota di vivacità e intensità al piatto, rendendolo un esperienza gastronomica indimenticabile. Preparata con cura e passione, la nostra carbonara vi trasporterà direttamente nelle trattorie romane, regalandovi un viaggio sensoriale unico e appagante.', (SELECT id FROM cuoco WHERE nome = 'Leonardo' AND cognome = 'Crozzoli'), '{/images/ricette/Carbonara.jpg}'); 

-- Inserimenti degli ingredienti
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Pomodoro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Olio extravergine d oliva');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Parmigiano Reggiano');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Melanzane');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Ricotta Salata');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Riso');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Mozzarella');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Piselli');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Panna');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Pesce');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Cozze');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Vongole');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Mandorle');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Cioccolato');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Guanciale');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Pecorino Romano');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Uova');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Pepe Nero');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Pasta');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Farina');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Burro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Latte');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Carne Macinata');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Salsa di Pomodoro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Besciamella');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Savoiardi');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Caffè');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Mascarpone');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Cacao Amaro');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Riso Carnaroli');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Brodo Vegetale');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Funghi Porcini');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Aglio');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Prezzemolo');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Formaggio Gorgonzola');
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_id_seq'), 'Noci');

-- Inserimenti delle righe ricetta
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Olio extravergine d oliva'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '30 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Parmigiano Reggiano'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '50 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Parmigiano Reggiano'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '40 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pecorino Romano'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), '100 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pepe Nero'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Cacio e Pepe'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '300 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Carne Macinata'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '400 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Salsa di Pomodoro'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '500 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Besciamella'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '300 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Mozzarella'), (SELECT id FROM ricetta WHERE nome = 'Lasagna'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Savoiardi'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Caffè'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '200 ml');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Mascarpone'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), '500 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Cacao Amaro'), (SELECT id FROM ricetta WHERE nome = 'Tiramisù'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Riso Carnaroli'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '300 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Brodo Vegetale'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '1 l');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Funghi Porcini'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '200 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Aglio'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), '2 spicchi');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Prezzemolo'), (SELECT id FROM ricetta WHERE nome = 'Risotto ai Funghi'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Guanciale'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '150 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pecorino Romano'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '100 gr');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Uova'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '3');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pepe Nero'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), 'q.b.');
INSERT INTO riga_ricetta (id, ingrediente_id, ricetta_id, quantita) VALUES(nextval('riga_ricetta_id_seq'), (SELECT id FROM ingrediente WHERE nome = 'Pasta'), (SELECT id FROM ricetta WHERE nome = 'Spaghetti Carbonara'), '200 gr');
