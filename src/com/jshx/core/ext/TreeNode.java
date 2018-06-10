package com.jshx.core.ext;

public class TreeNode
{
  private Boolean leaf;
  private String icon;
  private String iconCls;
  private String href;
  private String hrefTarget;
  private String id;
  private String text;
  private Boolean checked;
  private Boolean hasChildren;

  public TreeNode()
  {
  }

  public TreeNode(String id, String text)
  {
    this.id = id;
    this.text = text;
  }

  public TreeNode(String id, String text, Boolean checked)
  {
    this.id = id;
    this.text = text;
    this.checked = checked;
  }

  public TreeNode(String id, String text, String icon, Boolean checked)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
    this.checked = checked;
  }

  public TreeNode(String id, String text, String icon, Boolean checked, Boolean Leaf)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
    this.checked = checked;
    this.leaf = Leaf;
  }

  public TreeNode(Boolean Leaf, String id, String text, String icon)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
    this.leaf = Leaf;
  }

  public TreeNode(String id, String text, Boolean leaf, String iconCls, Boolean checked)
  {
    this.id = id;
    this.text = text;
    this.leaf = leaf;
    this.iconCls = iconCls;
    this.checked = checked;
  }

  public TreeNode(String id, String text, Boolean leaf, String iconCls) {
    this.id = id;
    this.text = text;
    this.leaf = leaf;
    this.iconCls = iconCls;
  }

  public TreeNode(String id, String text, String icon)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
  }

  public TreeNode(String id, String text, String icon, String href, String hrefTarget)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
    this.hrefTarget = hrefTarget;
    this.href = href;
  }

  public TreeNode(String id, String text, Boolean leaf, String icon, String href, String hrefTarget)
  {
    this.id = id;
    this.text = text;
    this.icon = icon;
    this.leaf = leaf;
    this.hrefTarget = hrefTarget;
    this.href = href;
  }

  public String getHref()
  {
    return this.href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getHrefTarget() {
    return this.hrefTarget;
  }

  public void setHrefTarget(String hrefTarget) {
    this.hrefTarget = hrefTarget;
  }

  public String getIconCls() {
    return this.iconCls;
  }

  public void setIconCls(String iconCls) {
    this.iconCls = iconCls;
  }

  public String getIcon() {
    return this.icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Boolean getHasChildren()
  {
    return this.hasChildren;
  }

  public void setHasChildren(Boolean hasChildren) {
    this.hasChildren = hasChildren;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Boolean getChecked() {
    return this.checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public Boolean getLeaf() {
    return this.leaf;
  }

  public void setLeaf(Boolean leaf) {
    this.leaf = leaf;
  }
}