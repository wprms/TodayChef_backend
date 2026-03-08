CREATE TABLE IF NOT EXISTS recipe_mgt (
    recipe_id BIGSERIAL PRIMARY KEY,
    user_sys_id VARCHAR(50) NOT NULL,
    recipe_title VARCHAR(200) NOT NULL,
    recipe_info TEXT,
    recipe_ingredients TEXT,
    recipe_steps TEXT NOT NULL DEFAULT '[]',
    view_count INTEGER NOT NULL DEFAULT 0,
    recipe_thumbnail_image TEXT,
    recipe_instagram_link TEXT,
    recipe_video_link TEXT,
    recipe_video_file TEXT,
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
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_ingredients TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_steps TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS view_count INTEGER;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_thumbnail_image TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_instagram_link TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_video_link TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS recipe_video_file TEXT;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_create_id VARCHAR(50);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_create_date TIMESTAMP;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_update_id VARCHAR(50);
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS rec_update_date TIMESTAMP;
ALTER TABLE recipe_mgt ADD COLUMN IF NOT EXISTS logical_del_flag CHAR(1);
UPDATE recipe_mgt SET view_count = 0 WHERE view_count IS NULL;

CREATE TABLE IF NOT EXISTS recipe_comment_mgt (
    comment_id BIGSERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL,
    user_sys_id VARCHAR(50) NOT NULL,
    comment_text TEXT NOT NULL,
    rec_create_id VARCHAR(50),
    rec_create_date TIMESTAMP NOT NULL DEFAULT now(),
    rec_update_id VARCHAR(50),
    rec_update_date TIMESTAMP NOT NULL DEFAULT now(),
    logical_del_flag CHAR(1) NOT NULL DEFAULT '0'
);

ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS comment_id BIGSERIAL;
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS recipe_id BIGINT;
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS user_sys_id VARCHAR(50);
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS comment_text TEXT;
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS rec_create_id VARCHAR(50);
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS rec_create_date TIMESTAMP;
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS rec_update_id VARCHAR(50);
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS rec_update_date TIMESTAMP;
ALTER TABLE recipe_comment_mgt ADD COLUMN IF NOT EXISTS logical_del_flag CHAR(1);

ALTER TABLE IF EXISTS member_mgt ADD COLUMN IF NOT EXISTS social_provider VARCHAR(30);
ALTER TABLE IF EXISTS member_mgt ADD COLUMN IF NOT EXISTS social_user_id VARCHAR(200);
CREATE INDEX IF NOT EXISTS idx_member_social_account ON member_mgt (social_provider, social_user_id);
