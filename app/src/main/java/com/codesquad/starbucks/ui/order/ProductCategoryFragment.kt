import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentProductCategoryBinding
import com.codesquad.starbucks.domain.model.Category
import com.codesquad.starbucks.ui.order.CategoryAdapter

class ProductCategoryFragment : Fragment() {

    private lateinit var binding: FragmentProductCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_product_category, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mug= Category(Constants.MUG, getString(R.string.label_mug_korean), getString(R.string.label_mug_english))
        val glass= Category(Constants.GLASS, getString(R.string.label_glass_korean), getString(R.string.label_glass_english))
        val thumbler= Category(
            Constants.THUBMLER, getString(R.string.label_thubmler_korean), getString(
                R.string.label_thumbler_english))
        val productCategory= listOf(mug,glass,thumbler)
        val productAdapter= CategoryAdapter()
        binding.rvCategoryItem.adapter= productAdapter
        productAdapter.submitEvents(productCategory)
    }

}