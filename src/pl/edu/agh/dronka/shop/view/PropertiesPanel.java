package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;



import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;
import pl.edu.agh.dronka.shop.model.ElectronicsItem;
import pl.edu.agh.dronka.shop.model.BookItem;
import pl.edu.agh.dronka.shop.model.MusicItem;
public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -2804446079853846996L;
	private ShopController shopController;

	private ItemFilter filter;

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();
		Category currentCategory = shopController.getCurrentCategory();
		ItemFilter filter = new ItemFilter(currentCategory);

		filter.getItemSpec().setCategory(currentCategory);
		add(createPropertyCheckbox("Tanie bo polskie", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				filter.getItemSpec().setPolish(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		add(createPropertyCheckbox("Używany", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				filter.getItemSpec().setSecondhand(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		switch (currentCategory) {
			case ELECTRONICS:
				add(createPropertyCheckbox("Mobilny", event -> {
					((ElectronicsItem) filter.getItemSpec()).setMobile(((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}));
				add(createPropertyCheckbox("Gwarancja", event -> {
					((ElectronicsItem) filter.getItemSpec()).setWarranty(((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}));
				break;
			case BOOKS:
				add(createPropertyCheckbox("Twarda oprawa", event -> {
					((BookItem) filter.getItemSpec()).setHardcover(((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}));
				break;
			case MUSIC:
				add(createPropertyCheckbox("Zawiera video", event -> {
					((MusicItem) filter.getItemSpec()).setIncludesVideo(((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}));
				break;
		}
	}

	private JCheckBox createPropertyCheckbox(String propertyName,
			ActionListener actionListener) {

		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}

}
