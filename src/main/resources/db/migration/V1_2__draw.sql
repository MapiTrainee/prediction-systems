USE prediction_system;

CREATE TABLE IF NOT EXISTS draw (
    date DATETIME NOT NULL,
    multi VARCHAR(60),
    ticket VARCHAR(10),
    result VARCHAR(10),
    PRIMARY KEY(date)
);

INSERT INTO draw VALUES ('2016-09-18 14:00:00','1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20','5,10,15','1,1,1');
INSERT INTO draw (date,ticket) VALUES ('2016-09-18 21:40:00','17,20,25');