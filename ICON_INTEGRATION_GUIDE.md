# Icon Integration Guide

## Step 1: Save Icons to Project
Create a folder `src/resources/icons/` and save these icons:
- `donation.png` - For Total Donations card
- `items.png` - For Total Items card  
- `requests.png` - For Pending Requests card
- `low-stock.png` - For Low Stock Items card
- `home-button.png` - For Dashboard Home button
- `user.png` - For Login Page

## Step 2: Update HomePage.java
Replace the createStatCard calls with:

```java
// Create stat cards with icons
try {
    ImageIcon itemsIcon = new ImageIcon(getClass().getResource("/resources/icons/items.png"));
    ImageIcon donationIcon = new ImageIcon(getClass().getResource("/resources/icons/donation.png"));
    ImageIcon requestsIcon = new ImageIcon(getClass().getResource("/resources/icons/requests.png"));
    ImageIcon lowStockIcon = new ImageIcon(getClass().getResource("/resources/icons/low-stock.png"));
    
    statsPanel.add(createStatCard("Total Items", "...", new Color(59, 130, 246), itemsIcon));
    statsPanel.add(createStatCard("Total Donations", "...", new Color(16, 185, 129), donationIcon));
    statsPanel.add(createStatCard("Pending Requests", "...", new Color(245, 158, 11), requestsIcon));
    statsPanel.add(createStatCard("Low Stock Items", "...", new Color(239, 68, 68), lowStockIcon));
} catch (Exception e) {
    // Fallback to text-only cards if icons not found
    statsPanel.add(createStatCard("📦 Total Items", "...", new Color(59, 130, 246)));
    statsPanel.add(createStatCard("🎁 Total Donations", "...", new Color(16, 185, 129)));
    statsPanel.add(createStatCard("⏳ Pending Requests", "...", new Color(245, 158, 11)));
    statsPanel.add(createStatCard("⚠ Low Stock Items", "...", new Color(239, 68, 68)));
}
```

## Step 3: Update createStatCard method
Add icon parameter:

```java
private JPanel createStatCard(String title, String value, Color color, ImageIcon icon) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(new Color(30, 41, 59));
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(color, 2),
        BorderFactory.createEmptyBorder(30, 30, 30, 30)));
    
    // Icon label
    JLabel iconLabel = new JLabel();
    if (icon != null) {
        Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(img));
    }
    iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    titleLabel.setForeground(new Color(156, 163, 175));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    JLabel valueLabel = new JLabel(value);
    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
    valueLabel.setForeground(Color.WHITE);
    valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    card.add(Box.createVerticalGlue());
    card.add(iconLabel);
    card.add(Box.createRigidArea(new Dimension(0, 10)));
    card.add(titleLabel);
    card.add(Box.createRigidArea(new Dimension(0, 10)));
    card.add(valueLabel);
    card.add(Box.createVerticalGlue());
    
    return card;
}
```

## Step 4: Update DashBoardPage.java for Home Button
```java
try {
    ImageIcon homeIcon = new ImageIcon(getClass().getResource("/resources/icons/home-button.png"));
    Image img = homeIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
    btnHome.setIcon(new ImageIcon(img));
    btnHome.setText(" Home");
} catch (Exception e) {
    btnHome.setText("🏠 Home");
}
```

## Step 5: Update LoginPage.java
```java
// Add user icon to login panel
try {
    ImageIcon userIcon = new ImageIcon(getClass().getResource("/resources/icons/user.png"));
    Image img = userIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
    JLabel userIconLabel = new JLabel(new ImageIcon(img));
    userIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Add icon above title
    gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
    loginPanel.add(userIconLabel, gbc);
    
    // Adjust title position
    gbc.gridy = 1;
    loginPanel.add(lblTitle, gbc);
} catch (Exception e) {
    // Keep existing layout if icon not found
}
```

## Instructions:
1. Create the `src/resources/icons/` folder
2. Save all 6 icons in that folder with the exact names mentioned
3. Apply the code changes above
4. Rebuild the project

The icons will enhance the visual appeal while maintaining fallback Unicode icons if files are missing.