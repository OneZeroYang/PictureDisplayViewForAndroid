# PictureDisplayViewForAndroid<br>
工程目录下build.gradle:
     
     
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
用法 :  implementation 'com.github.OneZeroYang:PictureDisplayViewForAndroid:Tag'

1.2.0版本：移除必须依赖jdk1.8<br>

1.3.0版本：封装PDPView<br>

PDPView<br> xml用法:


    <picturedisplayview.yangshijie.com.library.PDPView<br>
        android:id="@+id/myPDPView"<br>
        android:layout_width="match_parent"<br>
        android:layout_height="match_parent"><br>
    </picturedisplayview.yangshijie.com.library.PDPView><br>
    
    
    
代码:<br>


    public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_CHOOSE = 0xff1;
    private PDPView<Uri> myPDPView;
    private  PDPViewAdapter<Uri> pdpViewAdapter;
    private List<Uri> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 初始化控件
         */
        myPDPView = (PDPView) findViewById(R.id.myPDPView);
        /**
         * 初始化适配器
         */
        pdpViewAdapter = new PDPViewAdapter(this, data);
        /**
         * 设置适配器
         */
        myPDPView.setAdapter(pdpViewAdapter);
        /**
         * 
         * 设置监听回调
         */
        myPDPView.setCall(new PDPViewCall<Uri>() {

            /**
             * 添加图片回调  自行处理
             */
            @Override
            public void addImage() {
                Log.i(TAG, "addImage: ");
                openCamera();
            }


            /**
             * 图片点击事件
             * @param holder
             * @param p
             */
            @Override
            public void onImageEvent(BaseMyHolder holder, int p) {
                Log.i(TAG, "onImageEvent: ");
            }


            /**
             * 图片资源回调 ——————》  图片顺序改变时、增加、删除图片都会回调  ，回调后请自行删除列表的最后一项
             * @param list  
             */
            @Override
            public void onUpdate(List<Uri> list) {
                Log.i(TAG, "onUpdate: "+list);
                
            }
        });

    }

    /**
     * 选择照片
     */
    private void openCamera() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.of(MimeType.JPEG)) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(9) // 图片选择的最多数量
                //.gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE);
    }
    List<Uri> mSelected;

    /**
     * 选择图片后的回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);

            /**
             * 向适配器里面添加新增的图片
             */
            pdpViewAdapter.addItme(mSelected);
        }
    }
}


相关接口 setLastView(int lastView) 设置最后一个布局，通常为添加图片<br>
        setAnimationCoefficient(float animationCoefficient) 设置长按和松开时的放大缩小动画参数1为不变 越大放大的动画越大<br>
	setAnimationTime(int animationTime)设置动画时间<br>
	setOpenAnimation(boolean openAnimation)是否开启动画<br>
	


