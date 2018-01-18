package com.example.hello.shoppingcart2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hello.shoppingcart2.R;
import com.example.hello.shoppingcart2.presenter.PresenterImpl;
import com.example.hello.shoppingcart2.view.ICartView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MyAdapter extends BaseExpandableListAdapter {
	private final Context context;
	private final CartBean cartBean;
	private final List<CartBean.DataBean> list;
	private final ICartView iCartView;

	public MyAdapter(Context context, CartBean cartBean, ICartView iCartView) {
		this.context = context;
		this.cartBean = cartBean;
		this.list = cartBean.getData();
		this.iCartView = iCartView;
	}

	//组的个数
	@Override
	public int getGroupCount() {
		return list.size();
	}

	//每个组的孩子的长度
	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).getList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).getList().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
		ParentViewHolder parentViewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_parent, null);
			CheckBox parent_cb = convertView.findViewById(R.id.parent_cb);
			TextView parent_tv = convertView.findViewById(R.id.parent_tv);
			parentViewHolder = new ParentViewHolder(parent_cb, parent_tv);
			convertView.setTag(parentViewHolder);
		} else {
			parentViewHolder = (ParentViewHolder) convertView.getTag();
		}
		//赋值
		parentViewHolder.getParent_cb().setChecked(list.get(groupPosition).isParentIsSelected());
		parentViewHolder.getParent_tv().setText(list.get(groupPosition).getSellerName());
		//点击事件
		parentViewHolder.getParent_cb().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isCheked = list.get(groupPosition).isParentIsSelected();
				isCheked = !isCheked;
				list.get(groupPosition).setParentIsSelected(isCheked);
				//遍历子元素，全部置为true
				List<CartBean.ChildBean> list = MyAdapter.this.list.get(groupPosition).getList();
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setChildIsSelected(isCheked);
				}
				//通知界面
				notifyDataSetChanged();
				//计算
				PresenterImpl presenter = new PresenterImpl();
				presenter.jisuan(new ModelImpl(), cartBean, iCartView);
			}
		});


		return convertView;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, ViewGroup parent) {
		ChildeViewHolder childeViewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_child, null);
			CheckBox child_cb = convertView.findViewById(R.id.child_cb);
			ImageView child_img = convertView.findViewById(R.id.child_img);
			TextView child_title = convertView.findViewById(R.id.child_title);
			TextView child_price = convertView.findViewById(R.id.child_price);
			childeViewHolder = new ChildeViewHolder(child_cb, child_img, child_title, child_price);
			convertView.setTag(childeViewHolder);
		} else {
			childeViewHolder = (ChildeViewHolder) convertView.getTag();
		}
		//赋值
		childeViewHolder.getChild_cb().setChecked(list.get(groupPosition).getList().get(childPosition).isChildIsSelected());
		//获取图的地址

		String images = list.get(groupPosition).getList().get(childPosition).getImages();
		String imageUrl = images.split(".jpg")[0] + ".jpg";
		Glide.with(context).load(imageUrl).into(childeViewHolder.getChild_imge());
		childeViewHolder.getChild_title().setText(list.get(groupPosition).getList().get(childPosition).getTitle());
		childeViewHolder.getChild_price().setText(list.get(groupPosition).getList().get(childPosition).getBargainPrice() + "");
		//字条目中的checkbox点击状态
		//点击事件
		childeViewHolder.getChild_cb().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isCheked = list.get(groupPosition).getList().get(childPosition).isChildIsSelected();
				isCheked = !isCheked;
				list.get(groupPosition).getList().get(childPosition).setChildIsSelected(isCheked);
				//遍历
				List<CartBean.ChildBean> list1 = MyAdapter.this.list.get(groupPosition).getList();
				boolean flag = true;
				for (int i = 0; i < list1.size(); i++) {
					if (!list1.get(i).isChildIsSelected()) {
						flag = false;
					}
				}
				list.get(groupPosition).setParentIsSelected(flag);
				//通知改变
				notifyDataSetChanged();
				//计算
				PresenterImpl presenter = new PresenterImpl();
				presenter.jisuan(new ModelImpl(), cartBean, iCartView);
			}
		});
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	//优化
	class ParentViewHolder {
		public CheckBox parent_cb;
		public TextView parent_tv;

		public ParentViewHolder(CheckBox parent_cb, TextView parent_tv) {
			this.parent_cb = parent_cb;
			this.parent_tv = parent_tv;
		}

		public CheckBox getParent_cb() {
			return parent_cb;
		}

		public void setParent_cb(CheckBox parent_cb) {
			this.parent_cb = parent_cb;
		}

		public TextView getParent_tv() {
			return parent_tv;
		}

		public void setParent_tv(TextView parent_tv) {
			this.parent_tv = parent_tv;
		}
	}

	class ChildeViewHolder {
		public CheckBox child_cb;
		public ImageView child_imge;
		public TextView child_title;
		public TextView child_price;

		public ChildeViewHolder(CheckBox child_cb, ImageView child_imge, TextView child_title, TextView child_price) {
			this.child_cb = child_cb;
			this.child_imge = child_imge;
			this.child_title = child_title;
			this.child_price = child_price;
		}

		public CheckBox getChild_cb() {
			return child_cb;
		}

		public void setChild_cb(CheckBox child_cb) {
			this.child_cb = child_cb;
		}

		public ImageView getChild_imge() {
			return child_imge;
		}

		public void setChild_imge(ImageView child_imge) {
			this.child_imge = child_imge;
		}

		public TextView getChild_title() {
			return child_title;
		}

		public void setChild_title(TextView child_title) {
			this.child_title = child_title;
		}

		public TextView getChild_price() {
			return child_price;
		}

		public void setChild_price(TextView child_price) {
			this.child_price = child_price;
		}
	}
}
