package com.somanath.simplestickerprovider

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.somanath.simplestickerprovider.ui.base.BaseActivity
import com.somanath.simplestickerprovider.ui.theme.SimpleStickerProviderTheme
import com.somanath.simplestickerprovider.ui.usecase.IntentResolverUseCase
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var intentResolverUseCase : IntentResolverUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleStickerProviderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", onClick = {
                        val intent = intentResolverUseCase.createConsumerIntentToAddStickerPack("1", "Bttv")
//                        LaunchIntentCommand.Chooser(intent)
                        launchIntentToAddPackToSpecificPackage(intent)
//                        launchIntentToAddPackToChooser(intent)
                    })
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_PACK = 200
        const val RESULT_STRING_EXTRA = "validation_error"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE_ADD_PACK) {
            if (resultCode == Activity.RESULT_CANCELED) {
                if (intent != null) {
                    val bundle = intent.extras
                    if (bundle != null && bundle.containsKey(RESULT_STRING_EXTRA)) {
                        val validationError: String? = bundle.getString(RESULT_STRING_EXTRA)
                        validationError?.let {
//                            if (BuildConfig.DEBUG) {
//                                toast(ToastMessage.Error(message = Message(it)))
//                            }
                            Log.d("Mainactivity", "Validation failed. Error: $validationError")
                        }
                    } else {
                        Log.d("Mainactivity", "Cancelled but no validation error given.")
//                        toast(ToastMessage.Error(message = Message("Cancelled but no validation error given.")))
                    }
                } else {
                    Log.d("Mainactivity", "add_pack_fail_prompt_update_whatsapp")
//                    toast(ToastMessage.Info(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp)))
                }
            }
        }
    }
    private fun launchIntentToAddPackToChooser(intent: Intent) {
        try {
            startActivityForResult(Intent.createChooser(intent, getString(R.string.add_to_whatsapp)), REQUEST_CODE_ADD_PACK)
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.d("Mainactivity", "${e}")
//            toast(ToastMessage.Error(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp)))
        }
    }

    private fun launchIntentToAddPackToSpecificPackage(intent: Intent) {
        try {
            startActivityForResult(intent, REQUEST_CODE_ADD_PACK)
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.d("Mainactivity", "add_pack_fail_prompt_update_whatsapp")
//            toast(ToastMessage.Info(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp)))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick:()->Unit) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(30.dp).clickable {
          onClick()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleStickerProviderTheme {
        Greeting("Android", onClick = {

        })
    }
}