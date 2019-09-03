import com.android.custom.plugin.lib.CustomPluginTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

public class CustomPluginTest {
    @Test
    void testCustomPlugin() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'com.android.custom.plugin.lib'
        assertTrue(project.tasks.writeToFile instanceof CustomPluginTask)
    }
}