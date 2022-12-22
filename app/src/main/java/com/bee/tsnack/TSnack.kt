package com.bee.tsnack

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.tsnack.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout


/**
 * @author Tariq Hussain  <br/>
 * Custom snackbar for easily implementing
 * it almost provide all types of options and styling available for snackbar
 */
class TSnack(private val context: Context) {
    private var textColor: Int = ContextCompat.getColor(context, R.color.white)
    private var actionTextColor: Int = ContextCompat.getColor(context, R.color.teal_200)
    private var duration: Int = Snackbar.LENGTH_SHORT
    private var drawable = GradientDrawable()
    private var message: String = ""
    private var padding: Int = 0.toPx(context).toInt()
    private var customView: View? = null
    private var action: ((Snackbar) -> Unit)? = null
    private var customViewAction: ((View) -> Unit)? = null
    private var buttonName: String = ""
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var backgroundColor: Int = Color.TRANSPARENT
    private var borderWidth: Int = 0
    private var borderColor: Int = Color.TRANSPARENT
    private var cornerRadius: Float = 0f
    private var coordinateView: View? = null
    private var typeFaceTextView: Typeface? = null
    private var typeFaceActionBtn: Typeface? = null
    private var customDrawable: GradientDrawable? = null
    private lateinit var snackBar: Snackbar

    constructor(context: Context, view: View) : this(context) {
        coordinateView = view
    }

    /**
     * Use this method to specify the style of message text in snack bar e.g bold, italic
     * @param textTypeface provide typeface here also
     * @see Typeface.BOLD, Typeface.ITALIC
     */
    fun textTypeface(textTypeface: Typeface) {
        typeFaceTextView = textTypeface
    }

    /**
     * Use this method to specify the style of action in snackbar e.g bold, italic
     * @see Typeface.BOLD, Typeface.ITALIC
     */
    fun actionTypeface(actionTypeface: Typeface) {
        typeFaceActionBtn = actionTypeface
    }

    /**
     * If you want to use a custom view in your snackbar use this method
     * @param customViewAction provide view here
     */
    fun withCustomView(customViewAction: ((View) -> Unit)?) {
        this.customViewAction = customViewAction
    }

    /**
     * Use this method for gradient background of your snackbar
     * remember if you are using this properties like backgroundColor, border, cornerRadius will not work
     * so create your drawable accordingly
     * @param drawableId you have to provide drawable resource here
     */
    fun drawableResource(@DrawableRes drawableId: Int) {
        val draw = ContextCompat.getDrawable(context, drawableId)
        if (draw is GradientDrawable) {
            drawable(draw)
        }
    }

    /**
     * this works same like drawableResource() but it have the different parameter
     * @param drawable provide GradientDrawable here
     */
    fun drawable(drawable: GradientDrawable?) {
        this.customDrawable = drawable
    }

    /**
     * if you want to specify the action in your snackbar use this method
     * @param resId provide the text you want to show as an action
     * @param action is callback will return to see if you have anything to do in callback
     */
    fun withAction(@StringRes resId: Int, action: (Snackbar) -> Unit) {
        withAction(context.getString(resId), action)
    }

    /**
     * Same as withAction(resId: Int) works parameters are different
     * @param actionText directly provide String here to show as action
     */
    fun withAction(actionText: String, action: (Snackbar) -> Unit) {
        this.action = action
        this.buttonName = actionText
    }

    /**
     * Use this method to define padding in your snackbar
     * @param padding integer number which will be converted to pixels
     */
    fun padding(padding: Int) {
        this.padding = padding
    }

    /**
     * define your custom duration of snackbar to be visible
     * @param duration provide milliseconds or
     * @see Snackbar.LENGTH_SHORT
     */
    fun duration(duration: Int) {
        this.duration = duration
    }

    /**
     * use this method to provide string resource for the message of snackbar
     * @param message String resource which will be used as a message
     */
    fun messageText(@StringRes message: Int) {
        message(context.getString(message))
    }

    /**
     * works same messageText() but parameter is different
     * @param message define your string directly for the message
     */
    fun message(message: String) {
        this.message = message
    }

    /**
     * use this method to provide custom layout for your snackbar
     * @param layoutResource provide your layout here
     */
    fun customView(@LayoutRes layoutResource: Int) {
        customView(inflater.inflate(layoutResource, LinearLayout(context), false))
    }

    /**
     * works same as above customView works, but paramete is different
     * @param view provide view here
     */
    fun customView(view: View) {
        this.customView = view
    }

    /**
     * call this method to specify the border width and color for yor snackbar
     * @param dimenId provide dimen resource id here
     * @param colorId provide color resource id here
     */
    fun borderResource(@DimenRes dimenId: Int, @ColorRes colorId: Int) {
        border(
            context.resources.getDimension(dimenId).toInt(), ContextCompat.getColor(
                context,
                colorId
            )
        )
    }

    /**
     * do the same functionality as borderResource but parameters are different
     * @param width provide with directly as integer number
     * @param color provide color here
     */
    fun border(width: Int, @ColorInt color: Int) {
        this.borderWidth = width
        this.borderColor = color
    }

    /**
     * use this method to define your action color
     * @param colorId provide color resource id here
     *
     */
    fun actionTextColorResource(@ColorRes colorId: Int) {
        actionTextColor(ContextCompat.getColor(context, colorId))
    }

    /**
     * do the same functionality as actionTextColorResource() parameter is different
     * @param actionTextColor provide color here
     */
    fun actionTextColor(@ColorInt actionTextColor: Int) {
        this.actionTextColor = actionTextColor
    }


    /**
     * use this method to define corner radius using dimen resource
     * @param dimenId provide dimen resource id
     */
    fun cornerRadiusResource(@DimenRes dimenId: Int) {
        cornerRadius(context.resources.getDimension(dimenId))
    }

    /**
     * call this method to define corner radius
     * @param cornerRadius provide radius number directly here
     */
    fun cornerRadius(cornerRadius: Float) {
        this.cornerRadius = cornerRadius
    }

    /**
     * use this method to specify the background color of snackbar
     * @param colorId provide color resource id here
     */
    fun backgroundColorRes(@ColorRes colorId: Int) {
        backgroundColor(ContextCompat.getColor(context, colorId))
    }

    /**
     * do the same thing as backgroundColorRes do
     * @param color provide background color here
     */
    fun backgroundColor(@ColorInt color: Int) {
        this.backgroundColor = color
    }

    /**
     * use this method to define color of message text
     * @param colorId provide color resource id
     */
    fun textColorResource(@ColorRes colorId: Int) {
        textColor(ContextCompat.getColor(context, colorId))
    }

    /**
     * functionality is same as textColorResource
     * @param color provide color here
     */
    fun textColor(@ColorInt color: Int) {
        this.textColor = color
    }


    /**
     * this method will return the snackbar view
     */
    fun getView(): View? {
        return customView
    }

    /**
     * use this method to explicitly dismiss your snackbar at anytime
     */
    fun dismiss() {
        if (::snackBar.isInitialized) {
            snackBar.dismiss()
        }
    }

    /**
     * this method will show the snackbar
     * calling this method is necessary to show snackbar
     */
    fun show(): TSnack {
        if (coordinateView != null && coordinateView is CoordinatorLayout) {
            makeSnackbar(coordinateView!!)
        } else {
            (context as Activity).findViewById<View>(android.R.id.content)?.apply {
                makeSnackbar(this)
            }
        }
        snackBar.show()
        return this
    }

    /**
     * this method will show the snackbar
     * calling this method is necessary to show snackbar
     */
    inline fun show(func: TSnack.() -> Unit): TSnack {
        this.func()
        return this.show()
    }

    /**
     * this method will take the values from setters and make snackbar for you
     */
    private fun makeSnackbar(view: View) {
        snackBar = Snackbar.make(view, message, duration)
        val snackbarView = snackBar.view
        val snackbarLayout = snackbarView as Snackbar.SnackbarLayout
        val snackContentLayout =
            snackbarLayout.getChildAt(0) as SnackbarContentLayout

        if (customDrawable == null) {
            if (backgroundColor != 0) {
                drawable.setColor(backgroundColor)
            } else {
                drawable = snackbarLayout.background as GradientDrawable
            }
            drawable.cornerRadius = cornerRadius
            drawable.setStroke(borderWidth, borderColor)
        } else {
            drawable = customDrawable as GradientDrawable
        }

        val paddingLeft = snackbarLayout.paddingLeft
        val paddingRight = snackbarLayout.paddingRight

        snackbarLayout.setBackgroundColor(Color.TRANSPARENT)
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackContentLayout.setPadding(paddingLeft, 0, paddingRight, 0)
        snackContentLayout.background = drawable

        if (padding > 0) {
            snackbarLayout.setPadding(padding, 0, padding, padding)
        }

        if (customView == null) {
            val tvSnackbarTextView = snackContentLayout.getChildAt(0) as AppCompatTextView
            tvSnackbarTextView.setTextColor(textColor)
            if (typeFaceTextView != null) {
                tvSnackbarTextView.typeface = typeFaceTextView
            }
            val btnSnackbarActionButton = snackContentLayout.getChildAt(1) as AppCompatButton
            btnSnackbarActionButton.setTextColor(actionTextColor)
            if (typeFaceActionBtn != null) {
                btnSnackbarActionButton.typeface = typeFaceActionBtn
            }

            if (action != null) {
                snackBar.setAction(buttonName) {
                    action?.invoke(snackBar)
                }
            }
        } else {
            snackContentLayout.visibility = View.GONE
            snackbarLayout.addView(customView)
            customViewAction?.invoke(customView!!)
        }
    }

    /**
     * converter of interger to pixel value
     */
    private fun Int.toPx(context: Context): Float {
        return this * context.resources.displayMetrics.density
    }

}