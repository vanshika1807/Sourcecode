
SELECT author_id AS id, COUNT(*) AS view_count
FROM Views
WHERE author_id = viewer_id
GROUP BY author_id
ORDER BY view_count DESC;
