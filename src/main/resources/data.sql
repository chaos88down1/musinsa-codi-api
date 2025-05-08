-- DB Data Init

-- 브랜드 등록 (id 는 AUTO_INCREMENT)
INSERT INTO brand (name) VALUES ('A');
INSERT INTO brand (name) VALUES ('B');
INSERT INTO brand (name) VALUES ('C');
INSERT INTO brand (name) VALUES ('D');
INSERT INTO brand (name) VALUES ('E');
INSERT INTO brand (name) VALUES ('F');
INSERT INTO brand (name) VALUES ('G');
INSERT INTO brand (name) VALUES ('H');
INSERT INTO brand (name) VALUES ('I');

-- 상품 등록
INSERT INTO product (category, price, brand_id) VALUES ('TOP', 11200, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 5500, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 4200, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9000, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2000, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1700, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 1800, (SELECT id FROM brand WHERE name = 'A'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2300, (SELECT id FROM brand WHERE name = 'A'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10500, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 5900, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3800, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9100, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2100, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 2000, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2000, (SELECT id FROM brand WHERE name = 'B'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2200, (SELECT id FROM brand WHERE name = 'B'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10000, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 6200, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3300, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9200, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2200, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1900, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2200, (SELECT id FROM brand WHERE name = 'C'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2100, (SELECT id FROM brand WHERE name = 'C'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10100, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 5100, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3000, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9500, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2500, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1500, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2400, (SELECT id FROM brand WHERE name = 'D'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2000, (SELECT id FROM brand WHERE name = 'D'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10700, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 5000, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3800, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9900, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2300, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1800, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2100, (SELECT id FROM brand WHERE name = 'E'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2100, (SELECT id FROM brand WHERE name = 'E'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 11200, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 7200, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 4000, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9300, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2100, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1600, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2300, (SELECT id FROM brand WHERE name = 'F'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 1900, (SELECT id FROM brand WHERE name = 'F'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10500, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 5800, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3900, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9000, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2200, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1700, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2100, (SELECT id FROM brand WHERE name = 'G'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2000, (SELECT id FROM brand WHERE name = 'G'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 10800, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 6300, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3100, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9700, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2100, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1600, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 2000, (SELECT id FROM brand WHERE name = 'H'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2000, (SELECT id FROM brand WHERE name = 'H'));

INSERT INTO product (category, price, brand_id) VALUES ('TOP', 11400, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('OUTER', 6700, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('PANTS', 3200, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('SNEAKERS', 9500, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('BAG', 2400, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('HAT', 1700, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('SOCKS', 1700, (SELECT id FROM brand WHERE name = 'I'));
INSERT INTO product (category, price, brand_id) VALUES ('ACCESSORY', 2400, (SELECT id FROM brand WHERE name = 'I'));
