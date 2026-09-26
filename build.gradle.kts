// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

tasks.register<Exec>("syncComposeSkills") {
    group = "agent-skills"
    description = "Pulls and flattens latest Compose performance skills into .agent/skills and .github/skills"
    commandLine(
        "bash", "-c", """
        set -e
        TMP_DIR=${'$'}(mktemp -d)
        echo "Fetching latest Compose performance skills..."
        git clone --depth 1 https://github.com/skydoves/compose-performance-skills.git "${'$'}TMP_DIR"
        
        # Clean previous and recreate directories
        rm -rf .agent/skills .github/skills
        mkdir -p .agent/skills .github/skills
        
        # Copy each skill directory preserving its folder name
        find "${'$'}TMP_DIR" -type f -name "SKILL.md" | while read -r skill_file; do
            skill_dir=${'$'}(dirname "${'$'}skill_file")
            # Skip the root repo SKILL.md if it exists
            if [ "${'$'}skill_dir" != "${'$'}TMP_DIR" ]; then
                cp -r "${'$'}skill_dir" "${'$'}PWD/.agent/skills/"
                cp -r "${'$'}skill_dir" "${'$'}PWD/.github/skills/"
            fi
        done
        
        rm -rf "${'$'}TMP_DIR"
        echo "All individual Compose performance skills synced successfully!"
        """
    )
}