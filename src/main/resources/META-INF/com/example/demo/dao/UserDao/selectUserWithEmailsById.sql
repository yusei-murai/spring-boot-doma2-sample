SELECT 
    u.id AS userId,
    u.name AS userName,
    u.email AS userEmail,
    u.created_at AS userCreatedAt,
    u.updated_at AS userUpdatedAt,
    
    e.id AS emailId,
    e.mail AS emailMail,
    e.created_at AS emailCreatedAt,
    e.updated_at AS emailUpdatedAt
FROM users u
LEFT JOIN emails e ON u.id = e.user_id
WHERE u.id = /* id */1
ORDER BY u.id, e.id