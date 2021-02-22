import jenkins.model.Jenkins
import org.jenkinsci.plugins.*
import hudson.plugins.locale.PluginImpl

def instance = Jenkins.instance
def jenkinsCliDescriptor = instance.getDescriptor("jenkins.CLI").get()

// Configure EN locale
println("[INFO] Configuring locale")
PluginImpl localePlugin = (PluginImpl)instance.getPlugin("locale")
localePlugin.systemLocale = "en_US"
localePlugin.@ignoreAcceptLanguage=true

// Disable Jenkins CLI
if (jenkinsCliDescriptor.isEnabled()) {
    jenkinsCliDescriptor.setEnabled(false)
    println('[INFO] Jenkins CLI has been disabled.')
} else {
    println('[INFO] Nothing changed. Jenkins CLI already disabled.')
}

// println("--- Configuring git global options")
// def desc = instance.getDescriptor("hudson.plugins.git.GitSCM")
// desc.setGlobalConfigName("jenkins")
// desc.setGlobalConfigEmail("jenkins@example.com")
// desc.save()
