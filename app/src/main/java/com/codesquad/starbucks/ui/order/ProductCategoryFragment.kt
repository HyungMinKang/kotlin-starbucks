import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.databinding.FragmentProductCategoryBinding
import com.codesquad.starbucks.domain.model.Category
import com.codesquad.starbucks.ui.order.CategoryAdapter

class ProductCategoryFragment : Fragment() {

    private lateinit var binding: FragmentProductCategoryBinding
    private lateinit var navigator: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_product_category, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator= NavHostFragment.findNavController(this)
        val mug= Category(Constants.MUG, getString(R.string.label_mug_korean), getString(R.string.label_mug_english))
        val glass= Category(Constants.GLASS, getString(R.string.label_glass_korean), getString(R.string.label_glass_english))
        val thumbler= Category(
            Constants.THUBMLER, getString(R.string.label_thubmler_korean), getString(
                R.string.label_thumbler_english))
        val productCategory= listOf(mug,glass,thumbler)
        val productAdapter= CategoryAdapter {categoryName ->  (openCategoryDetail(categoryName)) }
        binding.rvCategoryItem.adapter= productAdapter
        productAdapter.submitEvents(productCategory)
    }

    private fun openCategoryDetail(categoryName:String){
        val categoryCD= when(categoryName){
            getString(R.string.label_mug_korean)->"W0000030.js"
            getString(R.string.label_glass_korean)->"W0000164.js"
            getString(R.string.label_thubmler_korean)->"W0000031.js"
            else->""
        }
        val bundle= bundleOf(Constants.CATEGORY_KEY to categoryName, Constants.CATEGORY_CD_KEY to categoryCD)
        navigator.navigate(R.id.action_navigation_order_to_categoryDetailFragment, bundle)
    }

}