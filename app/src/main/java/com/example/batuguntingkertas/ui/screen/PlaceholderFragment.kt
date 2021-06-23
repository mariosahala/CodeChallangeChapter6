@file:Suppress("RedundantNullableReturnType", "unused", "unused", "KDocUnresolvedReference",
    "PrivatePropertyName"
)

package com.example.batuguntingkertas.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.databinding.FragmentPlaceholderBinding


class PlaceholderFragment : Fragment() {
    private var _binding: FragmentPlaceholderBinding? = null
    private val binding get() = _binding!!
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private val ARG_SECTION_NUMBER = "section_number"
    var img: ImageView? = null

    private val onBoardImages = intArrayOf(R.raw.player, R.raw.comp)

    private val onBoardTitle = arrayOf("Batu Kertas Gunting", "KAWIBAWIBO")
    private val onBoardTitleSub = arrayOf("Bermain dengan Sesama Pemain","Bermain lawan Komputer")
    private val onBoardImageSub = arrayOf("Pemain dapat bermain dengan teman terdekat.", "Pemain dapat bermain dengan computer untuk menguji kehandalan bermain.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceholderBinding.inflate(inflater, container, false)
        val rootView = binding.root


        binding.placeHolderTvTitle.text = onBoardTitle[requireArguments().getInt(ARG_SECTION_NUMBER) - 1]
        binding.placeHolderTvTitleSub.text = onBoardTitleSub[requireArguments().getInt(ARG_SECTION_NUMBER) - 1]
        binding.placeHolderLottieAnim.setAnimation(onBoardImages[requireArguments().getInt(ARG_SECTION_NUMBER) - 1])
        binding.placeHolderTvImgSub.text = onBoardImageSub[requireArguments().getInt(ARG_SECTION_NUMBER) - 1]

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sectionNumber: Int) =
            PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}