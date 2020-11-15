package top.gtf35.breatheedittext

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editText = BreatheEditText(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            text = DEMO_TEXT.toEditable()
        }

        val rootView = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(editText)
        }

        setContentView(rootView)
    }

    companion object {
        const val DEMO_TEXT = "纯纯写作是一个追求绝不丢失、令人感到安心的写作应用。拥有极快的启动速度、丝滑流畅的写作体验，支持 Markdown 和 Material Design 2。不管是用于专业的小说创作，还是作为日常日记、便签之用，纯纯写作都能够提供 精致 又 体验极佳 的写作体验。\n" +
                "\n" +
                "　　令人感到安心，顺滑流畅的写作体验，如你所愿，这就是纯纯写作。我们希望写作能够回到原本的样子：快速、纯粹、随时、有安全感、具备良好的写作体验。\n" +
                "\n" +
                "　　纯纯写作实现了在 Android 上完美的行间距和段间距，它能够使您的文本看起来更加舒适，清晰。同时，它还实现了「快捷输入栏」和「格式化工具」，以及许多喜人的细节内容。\n" +
                "\n" +
                "　　最重要的，在使用这个编辑器时，它将会保证您的内容永远不会丢失，除非您主动将它清空。否则即使误操作将文本删除，或瞬间断电，您仍然能够从历史记录或备份中将它恢复。\n" +
                "\n" +
                "　　纯纯写作还在流畅体验上下了很多功夫，它拥有 完美的键盘响应逻辑。使用纯纯写作时，你能够往下拖动快捷输入栏来收起软键盘；能够点击文章某一行后，动态获得合适的光标焦点定位；能够在收起软键盘时，文章位置保持不动 ... 许多这样的细节内容，当你对比使用其他编辑器项目时，你才会发现纯纯写作做得更加出色、流畅，细致入微。\n" +
                "\n" +
                "　　蕴繁于简。许多编辑器该有的功能，纯纯写作也都没有落下，比如 WebDAV 云备份、段首缩进、生成长图片、指纹加锁、Markdown、更换信纸、夜间模式、单词联想输入、打字机模式、撤销、字数统计 等等。但即使这样，纯纯写作仍然保持了简约的设计风格，遵循 Material Design 设计，并且它还有一个像是哆啦 A 梦 时光机 的图标，寓意着「文字能够连接过去，遥想未来」，也照应了纯纯写作特别提供的「历史记录」功能。\n" +
                "\n" +
                "　　您能够最快速度到达灵感页面，能够随时随地中断和继续写作，这些我们都已经为您做好了。\n" +
                "\n" +
                "　　令人感到安心，顺滑流畅的写作体验，如你所愿，这就是纯纯写作，请享受写作吧！\n" +
                "\n" +
                "\n" +
                "\n" +
                "更多介绍：https://writer.drakeet.com\n" +
                "\n" +
                "《纯纯写作自动备份与云备份教程》：https://writer.drakeet.com/backups\n" +
                "\n" +
                "为了能够把更多时间和心思放在纯纯写作开发和改善体验本身，如果有任何建议、需求和问题，麻烦大家通过邮件联系我，虽然有时回复不那么即时，但一定都会回复：\n" +
                "\n" +
                "drakeet@drakeet.com\n" +
                "\n"
    }
}