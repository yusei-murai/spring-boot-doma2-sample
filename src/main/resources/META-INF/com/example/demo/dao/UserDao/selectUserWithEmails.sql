SELECT 
    u.id AS u_id,
    u.name AS u_name,
    u.email AS u_email,
    u.created_at AS u_created_at,
    u.updated_at AS u_updated_at,
    
    e.id AS u_emails_id,
    e.user_id AS u_emails_user_id,
    e.mail AS u_emails_mail,
    e.created_at AS u_emails_created_at,
    e.updated_at AS u_emails_updated_at
FROM users u
LEFT JOIN emails e ON u.id = e.user_id
ORDER BY u.id, e.id