import com.example.tymex_currencyconverter_test.MyApplication
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(MyApplication::class)
interface MyTestApplication{}