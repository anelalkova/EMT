CREATE MATERIALIZED VIEW accommodations_per_host AS
SELECT u.username,
       COUNT(a.id) AS num_accommodations
FROM app_user u
         LEFT JOIN accommodation a ON a.host_id = u.id
WHERE u.role = 'ROLE_HOST'
GROUP BY u.id, u.username;

CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT
    c.name AS country,
    COUNT(au.id) AS host_count
FROM app_user au
         JOIN country c ON au.country_id = c.id
WHERE au.role = 'ROLE_HOST'
GROUP BY c.name;
