package expo.modules.settings

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition


class CollectUsageModule : Module() {
    override fun definition() = ModuleDefinition {
        Name("CollectUsage")

        Function("startCollectionService") {
            // Launch the accessibility service
            return@Function startAccessibilityService()
        }

        Function("stopCollectionService") {
            // Stop the accessibility service if running
            return@Function stopAccessibilityService()
        }
    }

    private fun startAccessibilityService() {
        // Launch the service logic
        // This needs to be explicitly enabled by the user in Accessibility settings
        Log.d("CollectUsageModule", "Accessibility service started")
    }

    private fun stopAccessibilityService() {
        // Stop service logic
        Log.d("CollectUsageModule", "Accessibility service stopped")
    }
}

// The AccessibilityService class to capture events
class UsageAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event != null) {
            // Check the event type and handle only text-related events
            when (event.eventType) {
                AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED,
                AccessibilityEvent.TYPE_VIEW_CLICKED,
                AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                    val source: AccessibilityNodeInfo? = event.source
                    source?.let {
                        val capturedText = getTextFromNode(it)
                        if (!capturedText.isNullOrBlank()) {
                            Log.d("UsageAccessibilityService", "Captured Text: $capturedText")
                            // Here, you can send this text to your module
                        }
                    }
                }
            }
        }
    }

    override fun onInterrupt() {
        // Handle interruptions
        Log.d("UsageAccessibilityService", "Accessibility service interrupted")
    }

    // Helper function to extract text from AccessibilityNodeInfo
    private fun getTextFromNode(node: AccessibilityNodeInfo?): String? {
        return node?.text?.toString()
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        // Configure service info settings
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED or
                         AccessibilityEvent.TYPE_VIEW_CLICKED or
                         AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
            flags = AccessibilityServiceInfo.DEFAULT
        }
        this.serviceInfo = info

        Log.d("UsageAccessibilityService", "Accessibility service connected")
    }
}
