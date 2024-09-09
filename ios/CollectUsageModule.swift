import ExpoModulesCore

public class CollectUsageModule: Module {
  public func definition() -> ModuleDefinition {
    Name("CollectUsage")

    Function("getTheme") { () -> String in
      "system"
    }
  }
}
