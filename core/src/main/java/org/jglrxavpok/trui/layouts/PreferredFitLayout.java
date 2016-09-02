package org.jglrxavpok.trui.layouts;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;

import java.util.LinkedList;
import java.util.List;

import static org.jglrxavpok.trui.layouts.PreferredFitLayout.Align.CENTER;
import static org.jglrxavpok.trui.layouts.PreferredFitLayout.Align.LEFT;
import static org.jglrxavpok.trui.layouts.PreferredFitLayout.Align.RIGHT;

public class PreferredFitLayout extends Layout {

    private Align align;
    private float horizontalSpacing;
    private float verticalSpacing;

    public enum Align {
        CENTER, LEFT, RIGHT;
    }

    public PreferredFitLayout(TruiPanel owner) {
        super(owner);
        horizontalSpacing = verticalSpacing = 10f;
        align = CENTER;
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public float getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(float horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public float getVerticalSpacing() {
        return verticalSpacing;
    }

    public void setVerticalSpacing(float verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    @Override
    public void applyChildrenLayout() {
        float startX;
        float widthReduction;
        boolean xCentered;
        int direction = 1;

        startX = owner.getSize().x/2f;
        xCentered = true;
        widthReduction = 0.5f;
        switch(align) {
            case CENTER: {
                startX = owner.getSize().x / 2f;
                xCentered = true;
                widthReduction = 0.5f;
            }
            break;

            case LEFT: {
                startX = 0;
                xCentered = false;
                widthReduction = 0;
            }
            break;

            case RIGHT: {
                startX = owner.getSize().x;
                xCentered = false;
                widthReduction = 1f;
                direction = -1;
            }
            break;
        }

        float currentY = owner.getMargins().y;
        float currentWidth = 0f;
        List<TruiComponent> rowList = new LinkedList<TruiComponent>();
        for(TruiComponent c : owner.getChildren()) {
            if(currentWidth + owner.getSize().x + horizontalSpacing > owner.getSize().x+owner.getMargins().x*2) {
                currentY += move(rowList, xCentered, startX+owner.getMargins().x*2, currentY, widthReduction, direction, currentWidth)+ verticalSpacing;
                currentWidth = 0f;
                rowList.clear();
            }
            rowList.add(c);
            currentWidth+=c.getSize().x+ horizontalSpacing;
        }
        if(!rowList.isEmpty())
            currentY += move(rowList, xCentered, startX+owner.getMargins().x, currentY, widthReduction, direction, currentWidth)+ verticalSpacing;
        rowList.clear();
    }

    private float move(List<TruiComponent> rowList, boolean xCentered, float startX, float startY, float widthReduction, int direction, float currentWidth) {
        if(rowList.isEmpty())
            return 0f;
        float currentX = startX;
        final float maxH = findMax(Lists.transform(rowList, new Function<TruiComponent, Float>() {
            @Override
            public Float apply(TruiComponent input) {
                return input.getSize().y;
            }
        }));
        if(xCentered) {
            float x = startX-currentWidth/2f;
            for(TruiComponent child : rowList) {
                child.getPosition().x = x + owner.getPosition().x;
                child.getPosition().y = startY+owner.getPosition().y;
                child.invalidate();
                x += direction * (child.getSize().x + horizontalSpacing);
            }
        } else {
            for(TruiComponent child : rowList) {
                child.getPosition().x = currentX + owner.getPosition().x - widthReduction * child.getSize().x;
                child.getPosition().y = startY+owner.getPosition().y;
                child.invalidate();
                currentX += direction * (child.getSize().x + horizontalSpacing);
            }
        }
        return maxH;
    }

    private float findMax(List<Float> list) {
        float max = Float.NEGATIVE_INFINITY;
        for (float f : list) {
            if(f > max)
                max = f;
        }
        return max;
    }
}
