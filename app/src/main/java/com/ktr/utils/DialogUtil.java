package com.ktr.utils;//package com.ktr.utils;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.ktr.ktr_library.R;
//
//public class DialogUtil {
//
//    private volatile static DialogUtil instance;
//
//    private Dialog dialog;
//
//    public static DialogUtil getInstance(){
//        if(instance == null){
//            synchronized (DialogUtil.class) {
//                instance = new DialogUtil();
//            }
//        }
//        return instance;
//    }
//
//    public static interface OnDialogListener {
//
//        public void onOk(Object some);
//
//        public void onCancel(Object some);
//        
//        public void onTX(Object balance, Object acc);
//    }
//
//    /**
//     * 显示一个progressdialog
//     * @param context
//     * @return
//     */
//    public Dialog showProgressDialog(Context context) {
//
//        if(dialog == null || dialog.getOwnerActivity() != context) {
//            dialog = new Dialog(context, R.style.loading_dialog);
//        }
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.loading_dialog,null);
//        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);// 加载布局
//        dialog.setContentView(layout,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//
//        dialog.setCanceledOnTouchOutside(false);
//
//        if(context instanceof Activity){
//            dialog.setOwnerActivity((Activity)context);
//        }
//        return dialog;
//    }
//
//    /**
//     * @param context
//     * @param title
//     * @param content
//     * @param onDialogListener
//     */
//    public static final void showConfirmDialog(final Context context, final String title, final String content, final OnDialogListener onDialogListener) {
//
//        Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(content).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//        	
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onOk(null);
//                }
//            }
//        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onCancel(null);
//                }
//            }
//        }).create();
//        
//        dialog.show();
//    }
//    
//    /**
//     * @param context
//     * @param title
//     * @param content
//     * @param onDialogListener
//     */
//    public static final void showSelectConfirmDialog(final Context context, final String title, final String content, final OnDialogListener onDialogListener) {
//
//        Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(content).setPositiveButton("我的地址", new DialogInterface.OnClickListener() {
//        	
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onOk(null);
//                }
//            }
//        }).setNegativeButton("店铺地址", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onCancel(null);
//                }
//            }
//        }).create();
//        
//        dialog.show();
//    }
//    
//    /**
//     * @param context
//     * @param title
//     * @param content
//     * @param onDialogListener
//     */
//    public static final void showAddressConfirmDialog(final Context context, final String title, final String content, final OnDialogListener onDialogListener) {
//    	
//		View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null);
//		
//		final EditText editText = (EditText) view.findViewById(R.id.dialog_someDataEdit);
//		
//		Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(view).setPositiveButton("我的地址", new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onOk(editText.getText().toString());
//				}
//			}
//		}).setNegativeButton("店铺地址", new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onCancel(null);
//				}
//			}
//		}).create();
//		
//		dialog.setCancelable(false);
//		
//		dialog.show();
//	}
//    
//    /**
//     * @param context
//     * @param title
//     * @param content
//     * @param onDialogListener
//     */
//    public static final TextView updateAddressConfirmDialog(final Context context, final String title, final String content, final OnDialogListener onDialogListener) {
//    	
//		View view = LayoutInflater.from(context).inflate(R.layout.address_dialog_edit, null);
//		
//		TextView area_select_textView = (TextView) view.findViewById(R.id.area_select_textView);
//		
//		final EditText editText = (EditText) view.findViewById(R.id.dialog_someDataEdit);
//		
//		Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(view).setPositiveButton(android.R.string.ok, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onOk(editText.getText().toString());
//				}
//			}
//		}).setNegativeButton(android.R.string.cancel, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onCancel(null);
//				}
//			}
//		}).create();
//		
//		dialog.setCancelable(false);
//		
//		dialog.show();
//		
//		return area_select_textView;
//	}
//    
//    /**
//     * @param context
//     * @param title
//     * @param content
//     * @param onDialogListener
//     */
//    public static final void showExitConfirmDialog(final Context context, final String title, final String content, final OnDialogListener onDialogListener) {
//
//        Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(content).setPositiveButton("退出", new DialogInterface.OnClickListener() {
//        	
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onOk(null);
//                }
//            }
//        }).setNegativeButton("再看看", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (onDialogListener != null) {
//                    onDialogListener.onCancel(null);
//                }
//            }
//        }).create();
//        
//        dialog.show();
//    }
//	/**
//	 * 显示一个简单的输入框
//	 * @param context
//	 * @param title
//	 * @param onDialogListener
//	 */
//	public static final void showEditDialog(final Context context, final String title, final OnDialogListener onDialogListener) {
//		View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null);
//		final EditText editText = (EditText) view.findViewById(R.id.dialog_someDataEdit);
//		Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(view).setPositiveButton(android.R.string.ok, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onOk(editText.getText().toString());
//				}
//			}
//		}).create();
//		
//		dialog.setCancelable(false);
//		
//		dialog.show();
//	}
//	
//	/**
//	 * 显示一个简单的输入框
//	 * @param context
//	 * @param title
//	 * @param onDialogListener
//	 */
//	public static final void showEditDialogCanCancel(final Context context, final String title, final OnDialogListener onDialogListener) {
//		View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null);
//		final EditText editText = (EditText) view.findViewById(R.id.dialog_someDataEdit);
//		Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(view).setPositiveButton(android.R.string.ok, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onOk(editText.getText().toString());
//				}
//			}
//		}).setNegativeButton(android.R.string.cancel, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onCancel(null);
//				}
//			}
//		}).create();
//		
//		dialog.setCancelable(false);
//		
//		dialog.show();
//	}
//	
//	/**
//	 * 显示一个简单的输入框
//	 * @param context
//	 * @param title
//	 * @param onDialogListener
//	 */
//	public static final void showEditDialogTixian(final Context context, final String title, final OnDialogListener onDialogListener) {
//		View view = LayoutInflater.from(context).inflate(R.layout.tx_dialog_edit, null);
//		final EditText editText = (EditText) view.findViewById(R.id.dialog_someDataEdit);
//		editText.setHint("请输入您的提现金额：");
//		final EditText editText2 = (EditText) view.findViewById(R.id.dialog_acc);
//		editText2.setHint("请输入您的支付宝提现账号：");
//		
//		Dialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(view).setPositiveButton(android.R.string.ok, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
////					onDialogListener.onOk(editText.getText().toString());
//					onDialogListener.onTX(editText.getText().toString(), editText2.getText().toString());
//				}
//			}
//		}).setNegativeButton(android.R.string.cancel, new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if (onDialogListener != null) {
//					onDialogListener.onCancel(null);
//				}
//			}
//		}).create();
//		
//		dialog.setCancelable(false);
//		
//		dialog.show();
//	}
//}
