CREATE TABLE IF NOT EXISTS recipe_mgt (
    recipe_id BIGSERIAL PRIMARY KEY,
    user_sys_id VARCHAR(50) NOT NULL,
    recipe_title VARCHAR(200) NOT NULL,
    recipe_info TEXT,
    recipe_steps TEXT NOT NULL DEFAULT '[]',
    recipe_thumbnail_image TEXT,
    recipe_instagram_link TEXT,
    recipe_video_link TEXT,
    rec_create_id VARCHAR(50),
    rec_create_date TIMESTAMP NOT NULL DEFAULT now(),
    rec_update_id VARCHAR(50),
    rec_update_date TIMESTAMP NOT NULL DEFAULT now(),
    logical_del_flag CHAR(1) NOT NULL DEFAULT '0'
);

ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_id BIGSERIAL;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS user_sys_id VARCHAR(50);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_title VARCHAR(200);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_info TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_steps TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_thumbnail_image TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_instagram_link TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_video_link TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_create_id VARCHAR(50);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_create_date TIMESTAMP;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_update_id VARCHAR(50);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_update_date TIMESTAMP;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS logical_del_flag CHAR(1);
