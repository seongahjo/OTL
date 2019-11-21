DROP TABLE IF EXISTS player_url;
DROP TABLE IF EXISTS server_status;

CREATE TABLE player_url (
  client_id LONG NOT NULL,
  game_id LONG NOT NULL,
  url VARCHAR(1024) NOT NULL,
  PRIMARY KEY (client_id, game_id)
);

INSERT INTO player_url (client_id, game_id, url) VALUES
                                                       (0, 0, 'testurl00'),
                                                       (0, 1, 'testurl01'),
                                                       (0, 2, 'testurl02'),
                                                       (1, 0, 'testurl10'),
                                                       (1, 1, 'testurl11'),
                                                       (1, 2, 'testurl12');
CREATE TABLE server_status (
    key VARCHAR(256) PRIMARY KEY NOT NULL,
    value VARCHAR(1024) NOT NULL
);

INSERT INTO server_status (key, value) VALUES ( 'isCongested', 'False' );