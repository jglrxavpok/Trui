package org.jglrxavpok.trui.layouts;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;

import java.util.LinkedList;
import java.util.List;

public class PreferredFitLayout extends Layout {
    public PreferredFitLayout(TruiPanel owner) {
        super(owner);
    }

    @Override
    public void applyChildrenLayout() {
        float startX = 0f;
        float widthReduction = 0f;
        boolean xCentered = false;
        int direction = 1;

        startX = owner.getSize().x/2f;
        xCentered = true;
        widthReduction = 0.5f;
        /*layoutType match {
            case FlowLayout.CENTER =>
                startX = comp.w/2f
                xCentered = true
                widthReduction = 0.5f

            case FlowLayout.LEFT =>
                startX = 0
                xCentered = false
                widthReduction = 0

            case FlowLayout.RIGHT =>
                startX = comp.w
                xCentered = false
                widthReduction = 1f
                direction = -1

            case _ =>
        }*/

        float currentY = 0f;// TODO: owner.getMargins().y;
        float currentWidth = 0f;
        List<TruiComponent> rowList = new LinkedList<TruiComponent>();
        float xSpacing = 10f; // TODO
        float ySpacing = 10f; // TODO
        for(TruiComponent c : owner.getChildren()) {
            if(currentWidth + owner.getSize().x + xSpacing > owner.getSize().x/*+ TODO owner.getMargins().x*2*/) {
                currentY += move(rowList, xCentered, startX/*+owner.getMargins().x*2*/, currentY, widthReduction, direction, currentWidth)+ySpacing;
                currentWidth = 0f;
                rowList.clear();
            }
            rowList.add(c);
            currentWidth+=c.getSize().x+xSpacing;
        }
        if(!rowList.isEmpty())
            currentY += move(rowList, xCentered, startX/*TODO +owner.getMargins().x*/, currentY, widthReduction, direction, currentWidth)+ySpacing;
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
        float xSpacing = 10f; // TODO
        if(xCentered) {
            float x = startX-currentWidth/2f;
            for(TruiComponent child : rowList) {
                child.getPosition().x = x + owner.getPosition().x;
                child.getPosition().y = startY+owner.getPosition().y;
                child.invalidate();
                x += direction * (child.getSize().x + xSpacing);
            }
        } else {
            for(TruiComponent child : rowList) {
                child.getPosition().x = currentX + owner.getPosition().x - widthReduction * child.getSize().x;
                child.getPosition().y = startY+owner.getPosition().y;
                child.invalidate();
                currentX += direction * (child.getSize().x + xSpacing);
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
