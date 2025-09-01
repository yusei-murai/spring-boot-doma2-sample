SELECT 
    u.id AS u_id,
    u.name AS u_name,
    u.email AS u_email,
    u.created_at AS u_created_at,
    u.updated_at AS u_updated_at,
    
    e.id AS e_id,
    e.user_id AS e_user_id,
    e.mail AS e_mail,
    e.created_at AS e_created_at,
    e.updated_at AS e_updated_at
FROM users u
LEFT JOIN emails e ON u.id = e.user_id
WHERE u.id = /* id */1
ORDER BY u.id, e.id