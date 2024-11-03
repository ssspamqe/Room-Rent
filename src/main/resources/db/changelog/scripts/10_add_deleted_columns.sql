ALTER TABLE users
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE buyers
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE sellers
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE announcements
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE rooms
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE rents
    ADD COLUMN deleted boolean DEFAULT false;
ALTER TABLE user_passwords
    ADD COLUMN deleted boolean DEFAULT false;