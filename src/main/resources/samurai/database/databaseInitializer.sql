CREATE TABLE Player (
  DiscordId   BIGINT      NOT NULL PRIMARY KEY,
  OsuId       INT         NOT NULL UNIQUE,
  OsuName     VARCHAR(16) NOT NULL,
  GlobalRank  INT         NOT NULL,
  CountryRank INT         NOT NULL,
  Level       FLOAT       NOT NULL,
  RawPP       FLOAT       NOT NULL,
  Accuracy    FLOAT       NOT NULL,
  PlayCount   INT         NOT NULL,

  CountX      INT,
  CountS      INT,
  CountA      INT,

  Count300    INT,
  Count100    INT,
  Count50     INT,

  Modes       SMALLINT    NOT NULL DEFAULT 1,

  LastUpdated BIGINT      NOT NULL
);

CREATE TABLE Guild (
  GuildId BIGINT      NOT NULL PRIMARY KEY,
  Prefix  VARCHAR(16) NOT NULL,
  Modules BIGINT      NOT NULL
);

CREATE TABLE Chart (
  ChartId INT         NOT NULL PRIMARY KEY         GENERATED BY DEFAULT AS IDENTITY
                                                     ( START WITH 2001, INCREMENT BY 1),
  Name    VARCHAR(32) NOT NULL,
  IsSet   BOOLEAN     NOT NULL                     DEFAULT TRUE
);

CREATE TABLE ChartMap (
  ChartId  INT NOT NULL,
  MapSetId INT NOT NULL,
  CONSTRAINT ChartMap_PK PRIMARY KEY (ChartId, MapSetId),
  CONSTRAINT ChartId_FK FOREIGN KEY (ChartId) REFERENCES Chart (ChartId)
);

CREATE TABLE GuildChart (
  GuildId BIGINT NOT NULL,
  ChartId INT    NOT NULL,
  CONSTRAINT GuildChart_PK PRIMARY KEY (GuildId, ChartId),
  CONSTRAINT GuildId_FK FOREIGN KEY (GuildId) REFERENCES GUILD (GuildId),
  CONSTRAINT ChartId_FK2 FOREIGN KEY (ChartId) REFERENCES CHART (ChartId)
);

CREATE TABLE GuildPlayer (
  GuildId   BIGINT   NOT NULL,
  DiscordId BIGINT   NOT NULL,
  Mode      SMALLINT NOT NULL,
  CONSTRAINT GuildPlayer PRIMARY KEY (DiscordId, GuildId, Mode),
  CONSTRAINT DiscordId_FK FOREIGN KEY (DiscordId) REFERENCES Player (DiscordId),
  CONSTRAINT GuildId_FK2 FOREIGN KEY (GuildId) REFERENCES GUILD (GuildId)
);


CREATE TABLE ChannelMode (
  ChannelId BIGINT   NOT NULL,
  GuildId   BIGINT   NOT NULL,
  Mode      SMALLINT NOT NULL,
  CONSTRAINT ChannelMode PRIMARY KEY (ChannelId),
  CONSTRAINT GuildId_FK3 FOREIGN KEY (GuildId) REFERENCES Guild (GuildId)
);

CREATE TABLE ChannelLock (
  GuildId   BIGINT NOT NULL,
  ChannelId BIGINT NOT NULL,
  CONSTRAINT ChannelLock_PK PRIMARY KEY (GuildId, ChannelId),
  CONSTRAINT GUILDId_FK4 FOREIGN KEY (GuildId) REFERENCES Guild (GuildId)
);

CREATE TABLE MapSet (
  MapId INT NOT NULL,
  SetId INT NOT NULL,
  CONSTRAINT MapSet_PK PRIMARY KEY (MapId)
);

CREATE TABLE MemberPoints (
  DiscordId BIGINT NOT NULL,
  GuildId   BIGINT NOT NULL,
  Points    DOUBLE DEFAULT 0,
  CONSTRAINT Points_PK PRIMARY KEY (DiscordId, GuildId)
);

CREATE TABLE MemberInventory (
  DiscordId BIGINT NOT NULL,
  GuildId   BIGINT NOT NULL,
  SlotId    INT    NOT NULL,
  ItemId    INT    NOT NULL,
  Count     INT    NOT NULL,
  CONSTRAINT Inventory_PK PRIMARY KEY (DiscordId, GuildId, SlotId)
);

CREATE TABLE ItemCatalog (
  ItemId      INT PRIMARY KEY,
  Type        SMALLINT    NOT NULL,
  Name        VARCHAR(64) NOT NULL,
  Value       DOUBLE      NOT NULL,
  PropertyA   DOUBLE,
  PropertyB   DOUBLE,
  PropertyC   DOUBLE,
  PropertyD   DOUBLE,
  PropertyE   DOUBLE,
  PropertyF   DOUBLE,
  PropertyG   DOUBLE,
  PropertyH   DOUBLE,
  PropertyI   DOUBLE,
  Description VARCHAR(2000)
);

CREATE TABLE ItemDrop (
  ItemId INT PRIMARY KEY,
  DropId INT NOT NULL,
  Weight INT NOT NULL
);