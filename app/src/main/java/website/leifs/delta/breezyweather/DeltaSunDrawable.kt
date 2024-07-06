/**
 * This file is part of Breezy Weather Pixel Icon Provider.
 *
 * Breezy Weather Pixel Icon Provider is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, version 3 of the License.
 *
 * Breezy Weather Pixel Icon Provider is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Breezy Weather Pixel Icon Provider. If not, see <https://www.gnu.org/licenses/>.
 */

package website.leifs.delta.breezyweather

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import kotlin.math.min

class DeltaSunDrawable : Drawable() {
    private val mPaint = Paint().apply {
        isAntiAlias = true
    }

    private val mColors = intArrayOf(
        Color.rgb(249, 222, 129),
        Color.rgb(248, 193, 140)
    )
    private var mShader: Shader? = null
    private var mAlpha: Float = 1f
    private var mBounds: Rect
    private var mRadius = 0f
    private var mCX = 0f
    private var mCY = 0f

    init {
        mBounds = bounds
        ensurePosition(mBounds)
    }

    private fun ensurePosition(bounds: Rect) {
        mRadius = min(bounds.width(), bounds.height()) * 0.4062f
        mCX = (1.0 * bounds.width() / 2 + bounds.left).toFloat()
        mCY = (1.0 * bounds.height() / 2 + bounds.top).toFloat()
        mShader = LinearGradient(
            mCX,
            mCY - mRadius,
            mCX,
            mCY + mRadius,
            mColors[0],
            mColors[1],
            Shader.TileMode.CLAMP
        )
    }

    override fun onBoundsChange(bounds: Rect) {
        mBounds = bounds
        ensurePosition(bounds)
    }

    override fun draw(canvas: Canvas) {
        mPaint.alpha = (mAlpha * 255).toInt()
        mPaint.setShader(mShader)
        canvas.drawCircle(mCX, mCY, mRadius, mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mAlpha = alpha.toFloat()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.setColorFilter(colorFilter)
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun getIntrinsicWidth(): Int {
        return mBounds.width()
    }

    override fun getIntrinsicHeight(): Int {
        return mBounds.height()
    }
}