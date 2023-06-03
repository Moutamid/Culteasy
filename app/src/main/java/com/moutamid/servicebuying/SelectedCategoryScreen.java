package com.moutamid.servicebuying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.moutamid.servicebuying.adapter.CustomizedExpandableListAdapter;
import com.moutamid.servicebuying.databinding.ActivitySelectedCategoryScreenBinding;
import com.moutamid.servicebuying.model.Category;
import com.moutamid.servicebuying.model.SubCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectedCategoryScreen extends AppCompatActivity {

    private ActivitySelectedCategoryScreenBinding binding;
    private Category category;
    CustomizedExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<SubCategory>> expandableDetailList;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectedCategoryScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        expandableListView = findViewById(R.id.expandaleList);
        category = getIntent().getParcelableExtra("category");
        binding.name.setText(category.getName());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(SelectedCategoryScreen.this, MainScreen.class));
                finish();
            }
        });
        if (category.getName().equals("Agriculture Service")) {
            expandableDetailList = loadData1();
        }else if (category.getName().equals("Horticultural Service")) {
            expandableDetailList = loadData2();
        }else if (category.getName().equals("Veterinary Service Function")) {
            expandableDetailList = loadData3();
        }else if (category.getName().equals("Animal Husbandry Service")) {
            expandableDetailList = loadData4();
        }else if (category.getName().equals("Commercial Service")) {
            expandableDetailList = loadData5();
        }else if (category.getName().equals("Domestic Service")) {
            expandableDetailList = loadData6();
        }
        expandableTitleList = getGroupName();
        expandableListAdapter = new CustomizedExpandableListAdapter(this, 
                expandableTitleList, expandableDetailList);
        expandableListView.setAdapter(expandableListAdapter);

        for(int i=0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);
        // This method is called when the group is expanded
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
               // Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the group is collapsed
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the child in any group is clicked
        // via a toast method, it is shown to display the selected child item as a sample
        // we may need to add further steps according to the requirements
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                SubCategory child = expandableDetailList.get(
                        expandableTitleList.get(groupPosition)).get(
                        childPosition);
                Intent intent = new Intent(SelectedCategoryScreen.this, SubSelectedCategoryScreen.class);
                intent.putExtra("category",category);
                intent.putExtra("subCategory",child);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    private List<String> getGroupName() {
        List<String> group = new ArrayList<>();
        if (category.getName().equals("Agriculture Service")) {
            group.add("Soil Preparation Service");
            group.add("Sowing Service");
            group.add("Growing Service");
            group.add("Harvesting Service");
            group.add("Post Harvesting Service");
            group.add("Other Services");
        }else if (category.getName().equals("Horticultural Service")) {
            group.add("Fruits Cultivation Services");
            group.add("Vegetable Cultivation Services");
            group.add("Flower Cultivation Services");
            group.add("Gardening");
            group.add("Spices Crop Culture");
            group.add("Medical And Aromatic Plants Culture");
        }else if (category.getName().equals("Veterinary Service Function")) {
            group.add("Veterinary Services");
        }else if (category.getName().equals("Animal Husbandry Service")) {
            group.add("Animal Husbandry Services");
        }else if (category.getName().equals("Commercial Service")) {
            group.add("Commercial Services");
        }else if (category.getName().equals("Domestic Service")) {
            group.add("Domestic Services");
        }
        return group;
    }

    private HashMap<String, List<SubCategory>> loadData1() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();

        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("Ploughing","Ploughing is the foundation of successful farming, cultivating the soil and preparing it for optimal crop growth. With precise techniques and modern equipment, ploughing creates a fertile environment, promoting nutrient distribution and root development. Harness the power of ploughing to unleash the full potential of your fields and maximize your agricultural yield."));
        list1.add(new SubCategory("Levelling","Leveling is an essential step in land preparation, ensuring a smooth and even surface for efficient farming operations. Our leveling services utilize advanced techniques and equipment to eliminate undulations and optimize water distribution across the field. Experience the benefits of a well-leveled land, from improved irrigation efficiency to uniform crop growth, leading to higher yields and overall productivity."));
        list1.add(new SubCategory("Manuring","Manuring is a key component of sustainable farming, enriching the soil with essential nutrients for healthy plant growth. Our manuring services offer tailored solutions, analyzing soil composition and crop requirements to provide the ideal balance of organic and synthetic fertilizers. Experience the transformative power of effective manuring, promoting robust root development, increased nutrient absorption, and ultimately, enhanced crop quality and yield."));

        List<SubCategory> list2 = new ArrayList<SubCategory>();
        list2.add(new SubCategory("Broadcast Seeding","Broadcast seeding is a versatile and efficient method of sowing seeds across large areas, ensuring even distribution for optimal germination and plant establishment. Our broadcast seeding services utilize state-of-the-art equipment to disperse seeds effectively, saving time and labor. Embrace the advantages of broadcast seeding, from cost-effective coverage to improved crop density, leading to a successful and abundant harvest."));
        list2.add(new SubCategory("Transplanting","Transplanting is a meticulous technique that enables precise placement of seedlings into prepared soil, facilitating strong root development and minimizing transplant shock. Our transplanting services employ skilled experts and specialized tools to ensure each seedling is carefully transferred, promoting healthy growth and maximizing survival rates. Embrace the benefits of professional transplanting, from accelerated crop maturity to enhanced uniformity, ultimately resulting in thriving plants and increased yields."));

        List<SubCategory> list3 = new ArrayList<SubCategory>();
        list3.add(new SubCategory("Weed Management","Effective weed management is essential for maintaining the health and productivity of your crops. Our weed management services employ integrated approaches, combining manual and mechanical techniques with targeted herbicide applications to control and suppress weed growth. Experience the advantages of weed-free fields, from reduced competition for nutrients and sunlight to improved crop quality and yield, ensuring the success of your farming endeavors."));
        list3.add(new SubCategory("Fertilizer Application","Fertilizers are the lifeblood of agriculture, providing essential nutrients to nourish plants and optimize their growth. Our fertilizer solutions are carefully formulated to meet the specific nutritional needs of different crops, promoting healthy root development, vigorous foliage, and abundant yields. Experience the transformative power of high-quality fertilizers, unlocking the full potential of your crops and ensuring optimal nutrient uptake for sustainable and thriving agriculture."));
        list3.add(new SubCategory("Pestisides Application","Pesticides play a crucial role in protecting crops from harmful pests and diseases, ensuring their healthy growth and safeguarding yields. Our pesticide solutions are carefully selected and applied by trained professionals, targeting pests while minimizing environmental impact. Experience the peace of mind that comes with effective pest control, preserving crop quality, and maximizing productivity for a thriving agricultural enterprise."));
        list3.add(new SubCategory("Plant Protection","Plant protection is paramount in ensuring the health and vitality of crops, safeguarding them against pests, diseases, and environmental stresses. Our comprehensive plant protection services offer a holistic approach, combining preventive measures, timely interventions, and sustainable solutions. Experience the peace of mind that comes with robust plant protection, enabling your crops to flourish and thrive, ultimately leading to higher yields and a more resilient farming operation."));
        list3.add(new SubCategory("Crop Care","Crop care is the foundation of successful farming, encompassing a range of practices that promote the optimal growth and health of crops. Our crop care services provide tailored solutions, including timely irrigation, nutrient management, pest control, and weed suppression. Experience the benefits of comprehensive crop care, from improved plant vigor and disease resistance to higher yields and overall farm profitability, ensuring the success of your agricultural endeavors."));
        list3.add(new SubCategory("Irrigation","Irrigation is the lifeline of agriculture, delivering the essential water supply needed for crop growth and development. Our irrigation solutions are designed to maximize water efficiency, utilizing advanced technologies such as drip irrigation and precision sprinklers. Experience the advantages of efficient irrigation practices, from improved water distribution and reduced water wastage to enhanced crop yield and quality, ensuring optimal moisture levels for your fields and the success of your farming operations."));

        List<SubCategory> list4 = new ArrayList<SubCategory>();
        list4.add(new SubCategory("Reaping","Reaping is the culmination of a season’s hard work, where crops are harvested and gathered for further processing. Our reaping services utilize modern harvesting machinery and techniques to efficiently and effectively collect the fruits of your labor. Experience the satisfaction of a successful harvest, as we help you reap the rewards of your dedication and investment, bringing your crops from the field to the market with precision and efficiency."));
        list4.add(new SubCategory("Hauling","Hauling is the vital link between the farm and the market, ensuring the smooth transportation of harvested crops to their intended destinations. Our hauling services provide reliable and efficient transportation solutions, utilizing a fleet of well-maintained vehicles and experienced drivers. Experience the convenience of seamless crop hauling, as we take care of the logistics, ensuring your produce reaches its destination safely and on time, maximizing its market value and minimizing post-harvest losses."));
        list4.add(new SubCategory("Stacking","Stacking is a crucial step in post-harvest management, where crops are carefully arranged in organized and secure stacks for storage or transportation. Our stacking services employ skilled personnel and efficient stacking techniques to optimize space utilization and ensure stability. Experience the benefits of proper stacking, from improved storage capacity and ease of inventory management to reduced risk of damage, protecting the quality and value of your harvested crops."));
        list4.add(new SubCategory("Threshing","Threshing is a vital process in separating grain crops from their stalks or husks, preparing them for further processing and utilization. Our threshing services utilize advanced machinery and techniques to efficiently and effectively remove the grains from the plant material. Experience the benefits of professional threshing, from increased efficiency and reduced labor requirements to obtaining clean and quality grains, ensuring optimal utilization of your crop harvest."));
        list4.add(new SubCategory("Winnowing","Winnowing is an essential step in the post-harvest process, where grains are separated from chaff or other impurities through the use of airflow. Our winnowing services employ efficient techniques and equipment to remove lightweight particles and debris, ensuring the purity and quality of the harvested grains. Experience the advantages of professional winnowing, from improved grain cleanliness and reduced impurities to enhanced storage and market value, ensuring your crop achieves its maximum potential."));

        List<SubCategory> list5 = new ArrayList<SubCategory>();
        list5.add(new SubCategory("Industry Grade Cleaning","Industry-grade cleaning is a specialized service that focuses on thorough and meticulous cleaning of industrial facilities and equipment. Our cleaning services utilize advanced techniques, equipment, and cleaning agents to ensure a high level of cleanliness and hygiene. Experience the benefits of industry-grade cleaning, including improved operational efficiency, compliance with regulatory standards, and a safe and healthy work environment, contributing to the overall success and productivity of your industrial operations."));
        list5.add(new SubCategory("Drying","Drying is a critical step in post-harvest processing, where moisture is removed from agricultural products to enhance their shelf life and prevent spoilage. Our drying services utilize advanced drying technologies and controlled environments to efficiently reduce moisture levels while preserving the quality of the products. Experience the advantages of professional drying, including extended storage life, reduced risk of mold or bacterial growth, and enhanced market value, ensuring your harvested crops or agricultural products are ready for storage, transportation, or further processing."));
        list5.add(new SubCategory("Packaging And Storage","Packaging and storage are integral components of post-harvest management, ensuring the preservation and market readiness of agricultural products. Our packaging and storage services offer tailored solutions, utilizing appropriate packaging materials and techniques to protect the quality and freshness of your products. Experience the benefits of professional packaging and storage, including extended shelf life, enhanced product presentation, and efficient inventory management, allowing your agricultural goods to reach consumers in optimal condition and maximize their market value."));
        list5.add(new SubCategory("Transporting","Transportation plays a crucial role in the agricultural supply chain, ensuring the efficient and timely movement of agricultural products from farms to markets or processing facilities. Our transportation services provide reliable and well-managed logistics, utilizing a fleet of vehicles and experienced drivers to safely transport your agricultural goods. Experience the advantages of professional transportation, including on-time delivery, proper handling of perishable goods, and streamlined logistics, facilitating smooth operations and connecting your products to the intended destinations efficiently."));

        List<SubCategory> list6 = new ArrayList<SubCategory>();
        list6.add(new SubCategory("Farm Land Lease",""));
        list6.add(new SubCategory("Farm Insurance",""));
        list6.add(new SubCategory("Soil Testing",""));
        list6.add(new SubCategory("Crop Selection",""));
        list6.add(new SubCategory("Farming Education",""));
        list6.add(new SubCategory("Consult With Agro Expert",""));

        detailList.put("Soil Preparation Service", list1);
        detailList.put("Sowing Service", list2);
        detailList.put("Growing Service", list3);
        detailList.put("Harvesting Service",list4);
        detailList.put("Post Harvesting Service",list5);
        detailList.put("Other Services",list6);
        return detailList;
    }



    private HashMap<String, List<SubCategory>> loadData2() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();
        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("Soil Preparation","Soil preparation is crucial in fruit culture as it directly impacts the growth, health, and productivity of fruit-bearing plants. Our soil preparation services focus on creating the ideal soil conditions for fruit trees and plants to thrive. Through soil analysis, proper pH adjustments, organic matter incorporation, and appropriate nutrient balancing, we ensure that your orchard or fruit garden has a nutrient-rich and well-drained soil environment. Experience the benefits of professional soil preparation in fruit culture, such as improved root development, enhanced fruit quality, and increased overall yield, setting the stage for a fruitful and successful harvest."));
        list1.add(new SubCategory("Sowing","Sowing in fruit culture involves the careful and strategic planting of fruit seeds, seedlings, or grafts in order to establish healthy and productive fruit trees or plants. Our sowing services for fruit culture prioritize proper spacing, depth, and placement to ensure optimal root development and growth. With expertise in fruit-specific sowing techniques, we aim to maximize the success rate of germination, resulting in vigorous fruit-bearing trees or plants. Experience the advantages of professional sowing in fruit culture, from consistent and uniform plant establishment to a higher likelihood of early fruit production, setting the stage for a flourishing orchard or fruit garden."));
        list1.add(new SubCategory("Growing","Growing fruit culture encompasses a range of practices and techniques aimed at nurturing fruit-bearing plants to their full potential. Our fruit culture services offer comprehensive support throughout the growing process, including pruning, training, fertilization, pest and disease management, and irrigation. With a focus on promoting healthy growth, optimizing fruit quality, and maximizing yields, we ensure that your fruit plants receive the care they need to thrive. Experience the benefits of professional fruit culture, from increased fruit size and sweetness to improved disease resistance, resulting in a fruitful and sustainable orchard or fruit garden."));
        list1.add(new SubCategory("Harvesting","Harvesting in fruit culture marks the culmination of diligent care and cultivation, as ripe and ready-to-be-picked fruits are collected for consumption or commercial purposes. Our harvesting services for fruit culture are conducted with precision and expertise, ensuring that fruits are picked at the optimal stage of maturity for peak flavor and quality. With careful handling and attention to detail, we aim to maximize yield and minimize post-harvest losses. Experience the benefits of professional fruit harvesting, from timely and efficient picking to preserving fruit freshness, resulting in a bountiful harvest and the satisfaction of enjoying the fruits of your labor."));
        list1.add(new SubCategory("Post Harvesting","Post-harvest management in fruit culture is a critical phase that focuses on preserving the quality, extending the shelf life, and preparing harvested fruits for market distribution or processing. Our post-harvest services for fruit culture include careful sorting, cleaning, grading, and packaging to ensure that the fruits remain fresh and visually appealing. We also provide appropriate storage and temperature-controlled environments to maintain optimal conditions. Experience the advantages of professional post-harvest management, such as reduced spoilage, minimized loss of flavor and texture, and enhanced market value, ensuring that your harvested fruits reach consumers in their best possible condition."));

        List<SubCategory> list2 = new ArrayList<SubCategory>();
        list2.add(new SubCategory("Soil Preparation","Soil preparation is essential in vegetable culture as it lays the foundation for healthy plant growth and high-quality produce. Our soil preparation services for vegetable culture focus on creating an optimal growing environment. Through soil testing, nutrient analysis, organic matter incorporation, and proper soil amendments, we ensure that your vegetable garden or farm has fertile and well-balanced soil. This promotes strong root development, efficient nutrient uptake, and overall plant vigor. Experience the benefits of professional soil preparation in vegetable culture, such as increased yield, improved disease resistance, and enhanced vegetable quality, setting the stage for a successful and abundant harvest."));
        list2.add(new SubCategory("Sowing","Sowing in vegetable culture involves the careful and strategic planting of vegetable seeds or seedlings to establish healthy and productive vegetable crops. Our sowing services for vegetable culture focus on proper seed spacing, depth, and planting techniques to ensure optimal germination and plant establishment. We consider factors such as plant variety, climate, and seasonal requirements to ensure successful growth. Experience the benefits of professional sowing in vegetable culture, from consistent and uniform plant emergence to timely harvests of fresh and nutritious vegetables, ensuring a successful and rewarding vegetable garden or farm."));
        list2.add(new SubCategory("Growing","Growing vegetables requires expert care and attention to ensure optimal growth and yield. Our vegetable growing services encompass a range of practices, including proper irrigation, fertilization, pest and disease management, and crop monitoring. With a focus on sustainable and environmentally-friendly practices, we help cultivate robust and healthy vegetable plants that produce flavorful and nutritious crops."));
        list2.add(new SubCategory("Harvesting","Harvesting is a crucial stage in vegetable cultivation, where mature vegetables are carefully harvested for consumption or commercial purposes. Our harvesting services employ skilled harvesters who understand the appropriate harvesting techniques for each vegetable variety. By picking vegetables at the right stage of maturity, we ensure peak flavor, texture, and nutritional value, providing you with the freshest and highest quality produce."));
        list2.add(new SubCategory("Post Harvesting","Post-harvesting is a critical phase in vegetable cultivation that involves handling, sorting, and preserving harvested vegetables to maintain their freshness and market value. Our post-harvesting services include proper cleaning, grading, and packaging of vegetables to ensure their visual appeal and extend their shelf life. We also offer storage solutions with controlled environments to maintain optimal temperature and humidity conditions, preserving the quality and freshness of the vegetables."));

        List<SubCategory> list3 = new ArrayList<SubCategory>();
        list3.add(new SubCategory("Soil Preparation","Soil preparation is a crucial step in flower culture as it sets the stage for healthy plant growth and vibrant blooms. Our soil preparation services for flower culture focus on creating a fertile and well-drained soil environment. We assess soil composition, pH levels, and nutrient requirements to provide the necessary amendments and adjustments. By optimizing the soil conditions, we ensure that your flower garden or floral crops receive the ideal foundation for robust growth and stunning blossoms."));
        list3.add(new SubCategory("Sowing","Sowing in flower culture involves the careful planting of flower seeds or seedlings to establish beautiful and flourishing flower beds or fields. Our sowing services for flower culture emphasize proper seed spacing, depth, and planting techniques. We consider factors such as flower variety, bloom time, and aesthetic considerations to create visually appealing and harmonious flower arrangements. Experience the benefits of professional sowing in flower culture, from consistent and synchronized flowering to an abundant display of colors and fragrances."));
        list3.add(new SubCategory("Growing","Growing flower culture requires dedicated attention to the specific needs of each flower variety. Our growing services for flower culture encompass tailored care practices such as appropriate irrigation, fertilization, pest and disease management, and careful monitoring of growth stages. By providing optimal growing conditions and attentive plant care, we help ensure healthy and robust flower plants that produce stunning blooms, enhancing the beauty and allure of your flower garden or floral crops."));
        list3.add(new SubCategory("Harvesting","Harvesting in flower culture involves the precise and delicate collection of mature flowers for various purposes, such as floral arrangements or the production of essential oils. Our harvesting services for flower culture are conducted with precision, considering the ideal stage of bloom and the specific requirements of each flower variety. By employing proper techniques, we ensure the preservation of flower quality, longevity, and freshness, delivering blooms that captivate and inspire."));
        list3.add(new SubCategory("Post Harvesting","Post-harvesting in flower culture encompasses careful handling, processing, and preservation of harvested flowers to maintain their beauty and market value. Our post-harvesting services include timely cleaning, sorting, grading, and packaging of flowers to ensure their visual appeal and extended vase life. We also offer storage solutions with controlled environments to maintain optimal temperature and humidity conditions, prolonging the freshness and longevity of the blooms."));

        List<SubCategory> list4 = new ArrayList<SubCategory>();
        list4.add(new SubCategory("Tree Transplating","Tree transplanting in gardening involves the careful relocation of established trees to different areas within a garden or landscape. Our tree transplanting services utilize specialized techniques and equipment to ensure minimal stress and damage to the trees during the process. With expertise in tree root ball preservation, proper digging, and replanting methods, we aim to successfully transplant trees while promoting their continued growth and health. Experience the benefits of professional tree transplanting gardening, including the ability to reposition trees for aesthetic or functional purposes, preserving mature trees, and enhancing the overall beauty and design of your garden or landscape."));
        list4.add(new SubCategory("Garden Landscaping","Garden landscaping gardening focuses on the design, installation, and maintenance of aesthetically pleasing and functional gardens and landscapes. Our garden landscaping services involve the collaboration of skilled professionals who work closely with you to create customized garden designs that reflect your preferences, lifestyle, and environmental considerations. From selecting and arranging plants, hardscaping elements, and decorative features to implementing proper irrigation and sustainable gardening practices, we aim to transform your outdoor space into a captivating and harmonious garden oasis. Experience the advantages of professional garden landscaping gardening, from creating inviting outdoor living spaces to increasing property value and enhancing overall curb appeal."));
        list4.add(new SubCategory("Nursery Service","Nursery services in gardening play a vital role in the production and supply of high-quality plants and seedlings for gardens and landscapes. Our nursery services encompass the cultivation, care, and management of a diverse range of plants, including ornamentals, shrubs, trees, and herbs. With a focus on plant health and quality, we provide nursery services that include seed propagation, plant propagation through cuttings or grafting, nurturing young plants, and ensuring proper nutrition and disease control. Experience the benefits of professional nursery services in gardening, including access to a wide selection of healthy and well-established plants, expert advice on plant selection and care, and the convenience of sourcing plants from a trusted and reliable nursery."));
        list4.add(new SubCategory("Lawn Mowing","Lawn mowing is an essential aspect of garden maintenance, contributing to the overall health, appearance, and functionality of your lawn. Our lawn mowing services employ experienced gardeners equipped with professional-grade lawn mowing equipment. We follow industry best practices to ensure precise and even mowing, appropriate mowing heights, and the proper disposal of grass clippings. Regular lawn mowing promotes healthy grass growth, prevents weed infestation, and maintains a well-manicured and attractive lawn. Experience the benefits of professional lawn mowing gardening, including time savings, improved lawn health, and the enjoyment of a neat and well-kept outdoor space."));

        List<SubCategory> list5 = new ArrayList<SubCategory>();
        list5.add(new SubCategory("Soil Preparation","The soil preparation process for spices crops culture involves specific considerations to ensure the successful growth and development of these aromatic plants. It includes assessing the soil quality, pH levels, and nutrient content. Amendments such as organic matter, compost, and appropriate fertilizers are added to enhance soil fertility and structure, providing an ideal growing environment for spices crops."));
        list5.add(new SubCategory("Sowing","Sowing spices crops involves carefully selecting the right seeds or seedlings and planting them in prepared soil. The sowing process for spices crops may vary depending on the specific crop and its requirements. Proper spacing, depth, and planting techniques are employed to promote optimal germination and establishment of the spices crops."));
        list5.add(new SubCategory("Growing","Growing spices crops culture requires regular monitoring and care to ensure healthy growth and development. This includes providing adequate water, managing weeds, controlling pests and diseases, and implementing appropriate cultural practices specific to each spice crop. Nutrient management, including timely application of fertilizers or organic amendments, is crucial to support vigorous growth and optimize spice production."));
        list5.add(new SubCategory("Harvesting","Harvesting spices crops at the right time is essential to obtain the desired flavor, aroma, and quality. The harvesting process varies for different spices crops, with some harvested as whole plants, while others focus on specific plant parts such as leaves, flowers, or seeds. Proper techniques are employed to harvest, handle, and preserve the harvested spices crops to maintain their freshness and market value."));
        list5.add(new SubCategory("Post Harvesting","Post-harvest handling of spices crops is vital to ensure their longevity and quality. It involves activities such as cleaning, drying, curing, grading, and packaging. Proper storage conditions, including temperature and humidity control, are implemented to prevent spoilage, mold growth, or loss of flavor. Post-harvest processes play a crucial role in preserving the aroma, taste, and market value of the spices crops."));



        List<SubCategory> list6 = new ArrayList<SubCategory>();
        list6.add(new SubCategory("Soil Preparation","Soil preparation for medicinal and aromatic plants culture involves specific considerations to create an optimal growing environment. It includes assessing the soil composition, pH levels, and nutrient content. Amendments such as organic matter, compost, and suitable fertilizers are added to improve soil fertility, structure, and nutrient availability, ensuring the ideal conditions for the growth of medicinal and aromatic plants."));
        list6.add(new SubCategory("Sowing","Sowing medicinal and aromatic plants involves carefully selecting high-quality seeds or healthy seedlings and planting them in prepared soil. The sowing process may vary depending on the specific plant species and their requirements. Proper spacing, planting depth, and techniques are employed to promote successful germination and establishment of medicinal and aromatic plants."));
        list6.add(new SubCategory("Growing","Growing medicinal and aromatic plants culture requires ongoing care and attention to support their healthy growth and development. This includes providing adequate water, managing weeds, controlling pests and diseases, and implementing suitable cultural practices specific to each plant species. Nutrient management, such as timely application of organic or mineral fertilizers, is essential to ensure optimal plant growth, essential oil production, and medicinal properties."));
        list6.add(new SubCategory("Harvesting","Harvesting medicinal and aromatic plants at the right stage of growth is crucial to preserve their therapeutic and aromatic properties. The harvesting process varies for different plant species, as it may involve harvesting leaves, flowers, stems, or roots. Proper techniques are employed to harvest, handle, and preserve the harvested parts, ensuring the retention of their medicinal qualities and aromatic characteristics."));
        list6.add(new SubCategory("Post Harvesting","Post-harvest handling of medicinal and aromatic plants plays a vital role in maintaining their quality and value. It includes cleaning, drying, and processing the harvested plant material. Special techniques may be employed, such as steam distillation or cold-pressing, to extract essential oils from aromatic plants. Proper storage conditions, packaging, and labeling are implemented to ensure the longevity and marketability of the medicinal and aromatic plants."));

        detailList.put("Fruits Cultivation Services", list1);
        detailList.put("Vegetable Cultivation Services", list2);
        detailList.put("Flower Cultivation Services", list3);
        detailList.put("Gardening", list4);
        detailList.put("Spices Crop Culture",list5);
        detailList.put("Medical And Aromatic Plants Culture",list6);

        return detailList;
    }
    private HashMap<String, List<SubCategory>> loadData3() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();

        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("Treatment of Animals","We provide comprehensive treatment services for animals, catering to their health and well-being. Our team of experienced professionals is dedicated to delivering high-quality veterinary care and compassionate treatment to all animals in need. From routine check-ups and vaccinations to specialized treatments and surgeries, we offer a wide range of services to ensure the optimal health and happiness of your beloved pets or animals under your care. Our commitment to providing exceptional animal treatment stems from our passion for promoting the welfare of animals and building strong, lasting relationships with their owners. Rest assured that your animals will receive the utmost care and attention they deserve at our facility."));
        list1.add(new SubCategory("Biological Samples Examination","Our company specializes in the examination of biological samples, offering comprehensive services to analyze and assess various types of samples for research, diagnostics, or forensic purposes. Our team of skilled laboratory technicians and scientists utilize advanced techniques and cutting-edge equipment to examine and study biological samples, including blood, tissue, urine, DNA, and more. With a strong focus on accuracy, reliability, and confidentiality, we provide detailed reports and insights based on the analysis of these samples. Whether you require disease diagnostics, genetic testing, or research support, our biological sample examination services ensure precise and valuable results to meet your specific needs."));
        //list1.add(new SubCategory("Immunisation of Animals",""));
        list1.add(new SubCategory("Training of Farmers","Training farmers is a vital aspect of agricultural development and improvement in farming practices. Our company is dedicated to providing comprehensive training programs for farmers, equipping them with the knowledge, skills, and resources necessary to enhance their agricultural productivity and sustainability. Our training sessions cover a wide range of topics, including crop cultivation techniques, soil management, pest and disease control, irrigation practices, post-harvest handling, and market strategies. Through hands-on demonstrations, interactive workshops, and access to expert guidance, we empower farmers to make informed decisions, adopt modern farming methods, and effectively address challenges in their agricultural operations. By investing in farmer training, we aim to support their professional growth, promote sustainable farming practices, and contribute to the overall advancement of the agricultural industry."));
        list1.add(new SubCategory("Castration","Castration is a surgical procedure performed on male animals to remove the testicles, rendering them sterile and preventing reproduction. The process involves the removal of the testes, typically under anesthesia, to eliminate the production of sperm and reduce the production of male hormones such as testosterone. Castration is commonly carried out in various animal species, including dogs, cats, horses, and livestock, for reasons such as population control, behavior modification, or medical necessity. Our professional veterinary team provides safe and compassionate castration services, ensuring the well-being of the animals and adhering to established standards and protocols."));
        list1.add(new SubCategory("Artificial Insemination in Cattle and Buffalos","Artificial insemination (AI) in cattle and buffaloes is a widely practiced reproductive technique that involves the introduction of semen from selected male animals into the reproductive tract of female animals, without natural mating. Our company specializes in providing artificial insemination services for cattle and buffaloes, offering a range of benefits for livestock breeders and farmers."));
        list1.add(new SubCategory("Pregnancy Diagnosis ","Pregnancy diagnosis is a crucial step in the reproductive management of animals, allowing farmers and breeders to determine if female animals are pregnant and monitor the progress of pregnancies. Our company offers reliable and accurate pregnancy diagnosis services for various animal species, including cattle, buffaloes, sheep, goats, and horses."));
        list1.add(new SubCategory("Deworming of Animals","Deworming is an essential component of animal health management, aimed at controlling and preventing internal parasite infestations in various animal species. Our company offers comprehensive deworming services for livestock, companion animals, and other animals in need.\n" +
                "\n" +
                "Internal parasites, such as worms, can have detrimental effects on the overall health and productivity of animals. They can cause weight loss, poor growth, decreased milk production, anemia, and other health issues. Deworming helps to eliminate or control these parasites, reducing the risk of infection and improving the well-being of the animals."));
        list1.add(new SubCategory("Organisation of Animal Health Camps","At our company, we specialize in organizing Animal Health Camps, dedicated to providing comprehensive veterinary care and health services to animals in need. We understand the importance of accessible and affordable veterinary care, particularly in underserved communities or remote areas where access to veterinary services may be limited."));
        list1.add(new SubCategory("Mobile Veterinary Services","At our company, we are proud to offer comprehensive Mobile Veterinary Services, bringing professional and compassionate veterinary care directly to the doorstep of our clients. We understand the importance of convenience and accessibility when it comes to providing healthcare for animals, whether they are beloved pets or livestock. Our mobile veterinary unit is fully equipped with state-of-the-art medical equipment and supplies, allowing our experienced veterinarians to deliver a wide range of services in the comfort of your own home or farm."));
        list1.add(new SubCategory("Disaster Management Activities","At our company, we specialize in providing comprehensive Disaster Management Activity services, aimed at preparing communities and organizations for potential disasters and effectively responding to crisis situations. We understand the critical importance of proactive planning, preparedness, and rapid response in mitigating the impact of disasters on both human and animal populations."));
        list1.add(new SubCategory("Fodder Cultivation Activities ","At our company, we specialize in providing comprehensive Fodder Cultivation Activities services, aimed at supporting livestock farmers and ensuring a consistent and nutritious food supply for their animals. Fodder cultivation plays a crucial role in maintaining the health and productivity of livestock, and our team is dedicated to offering expert guidance and assistance in this important aspect of animal husbandry.\n" +
                "\n" +
                "Our experienced team takes pride in planning and executing well-organized Animal Health Camps, bringing together qualified veterinarians, skilled technicians, and necessary equipment and supplies. We collaborate with local communities, animal welfare organizations, and community leaders to ensure the success and impact of each camp."));
        list1.add(new SubCategory("Nutritional Management of Animals","At our company, we specialize in providing comprehensive Nutritional Management of Animal services, focusing on optimizing the dietary needs and overall nutrition of animals. We understand the critical role that proper nutrition plays in maintaining the health, growth, and productivity of animals.\n" +
                "\n" +
                "Our team of experienced professionals works closely with animal owners, farmers, and livestock managers to develop customized nutritional management plans tailored to the specific needs of each species and individual animal. We take into account factors such as age, breed, physiological status, and environmental conditions to formulate well-balanced and appropriate diets."));
        list1.add(new SubCategory("Animal Husbandry Extention Services","At our company, we take pride in offering Animal Husbandry Extension Services, dedicated to providing valuable knowledge, guidance, and support to livestock farmers and animal owners. We understand that successful animal husbandry practices are essential for the health, productivity, and overall well-being of animals.\n" +
                "\n" +
                "Our experienced team of animal husbandry experts works closely with farmers and animal owners to address their specific needs and challenges. Through our extension services, we aim to educate and empower individuals with the latest advancements and best practices in animal husbandry."));
        list1.add(new SubCategory("Dairy, Poultry, Small Animal Activities","At our company, we take pride in offering a wide range of activities and services in the areas of Dairy, Poultry, and Small Animals. Whether you are a dairy farmer, poultry enthusiast, or small animal owner, we have tailored services to meet your specific needs. Here's an overview of what we provide:\n" +
                "- Dairy Activities: For dairy farmers, we offer a comprehensive range of services to support the management and productivity of dairy herds. This includes professional consultancy on dairy farm setup and management, breed selection, nutrition planning, reproductive management, milk quality control, and overall herd health management. Our experienced team of dairy experts is dedicated to helping farmers optimize their milk production and ensure the well-being of their dairy animals.\n" +
                "\n" +
                "- Poultry Activities: We provide a range of services for poultry enthusiasts, whether you have a small backyard flock or a larger-scale poultry operation. Our services encompass poultry management consultancy, breed selection, vaccination programs, nutrition guidance, housing and environmental management, biosecurity measures, and disease control. We strive to assist poultry keepers in maximizing their flock’s health, productivity, and overall performance.\n" +
                "\n" +
                "- Small Animal Activities: We cater to the needs of small animal owners, including pet owners, hobbyists, and small-scale breeders. Our services encompass pet healthcare, preventive care, vaccination programs, nutrition counseling, parasite control, grooming, and general wellness examinations. We aim to provide comprehensive care for small animals, ensuring their happiness, health, and longevity.\n" +
                "\n"));
        list1.add(new SubCategory("Organization of Fair & Exhibition","At our company, we specialize in organizing Fairs & Exhibitions that serve as dynamic platforms for showcasing diverse industries, fostering economic growth, and connecting businesses with their target audience. With our expertise in event management, we strive to create engaging and memorable experiences for participants and visitors alike.\n" +
                "\n" +
                "When it comes to organizing Fairs & Exhibitions, we meticulously plan every aspect to ensure a seamless and successful event. From venue selection to exhibitor coordination, marketing strategies, and on-site logistics, we handle it all. Our dedicated team collaborates closely with clients to understand their goals, vision, and target audience, tailoring the event to their specific requirements."));
        list1.add(new SubCategory("Insurance of Animals","At our company, we specialize in providing comprehensive insurance services for animals, offering peace of mind and financial protection to animal owners. We understand the importance of safeguarding the health and well-being of animals, whether they are beloved pets, working animals, or valuable livestock.\n" +
                "\n" +
                "Our animal insurance policies are designed to cover a wide range of risks and uncertainties that animals may face throughout their lives. We offer flexible coverage options that can be tailored to meet the specific needs of each animal owner. From accident and illness coverage to preventive care and emergency medical expenses, our insurance plans provide comprehensive protection against unexpected veterinary costs."));


        detailList.put("Veterinary Services", list1);


        return detailList;
    }
    private HashMap<String, List<SubCategory>> loadData4() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();

        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("Dairy Farming","Dairy farming is a specialized branch of agriculture that focuses on the production of milk and dairy products. It involves the rearing of dairy cattle, such as cows or goats, for milk production. Dairy farmers are responsible for various aspects of the operation, including animal care, feeding, milking, and maintaining proper hygiene and sanitation. They play a crucial role in ensuring a steady supply of high-quality milk and dairy products for consumers."));
        list1.add(new SubCategory("Poultry Farming","Poultry farming refers to the practice of raising domesticated birds, such as chickens, turkeys, ducks, or geese, for their meat, eggs, or feathers. Poultry farms can range in scale from small backyard operations to large commercial enterprises. Poultry farmers are involved in the management of poultry housing, nutrition, health, and overall flock management. They ensure the well-being of the birds, monitor egg production or meat quality, and implement biosecurity measures to prevent the spread of diseases."));
        list1.add(new SubCategory("Bee Farming","Bee farming, also called apiculture or beekeeping, revolves around the management and cultivation of honey bee colonies. Beekeepers maintain beehives, provide suitable housing for bees, and manage their health and productivity. Bee farming focuses on honey production, but it also plays a crucial role in pollination, benefiting both agricultural crops and wild plant species. Beekeepers extract honey, harvest beeswax, and may also provide other products like pollen, royal jelly, or propolis. They contribute to the preservation of bee populations and the ecological balance of pollination-dependent ecosystems."));
        list1.add(new SubCategory("Fish Farming","Fish farming, also known as aquaculture, involves the cultivation of fish and other aquatic organisms in controlled environments, such as ponds, tanks, or cages. Fish farmers rear fish species suited for aquaculture, including freshwater and marine fish. They oversee the feeding, water quality management, disease prevention, and overall growth of the fish. Fish farming plays a significant role in meeting the growing demand for seafood, reducing pressure on wild fish populations, and promoting sustainable fish production."));

        detailList.put("Animal Husbandry Services", list1);
        return detailList;
    }
    private HashMap<String, List<SubCategory>> loadData5() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();
        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("Landscaping of CORPORATE","At our company, we specialize in providing exceptional landscaping services tailored specifically for corporate environments. We understand the importance of creating a visually appealing and harmonious outdoor space that aligns with your company's brand image and enhances the overall atmosphere of your premises.\n" +
                "\n" +
                "Our team of experienced landscape designers and horticulturists work closely with you to develop a customized landscaping plan that meets your unique requirements and reflects your corporate identity. Whether you have a large corporate campus, an office building, or a commercial property, we have the expertise to transform your outdoor areas into stunning landscapes that leave a lasting impression."));
        list1.add(new SubCategory("Landscaping of Government Offices","At our company, we specialise in providing top-notch landscaping services tailored specifically for government offices. We understand the unique requirements and standards associated with government properties, and we are dedicated to delivering exceptional results that align with your office's image and enhance the overall aesthetics and functionality of the surrounding outdoor areas.\n" +
                "\n" +
                "Our team of experienced landscape designers and horticulturists work closely with government agencies to develop customised landscaping plans that meet their specific needs and adhere to regulatory guidelines. We recognize the importance of creating inviting, well-maintained landscapes that promote a positive environment for employees and visitors alike."));
        list1.add(new SubCategory("Landscaping of Establishments","At our company, we take pride in offering top-quality landscaping services tailored specifically for a wide range of establishments. We understand that the outdoor environment plays a crucial role in creating a positive and welcoming atmosphere for your establishment, whether it's a residential complex, retail center, educational institution, healthcare facility, or any other type of property.\n" +
                "\n" +
                "Our team of skilled landscapers and designers is dedicated to transforming your outdoor space into an inviting and aesthetically pleasing environment that enhances the overall experience for your visitors, tenants, or customers."));
        list1.add(new SubCategory("Landscaping of shopping mall and Apartments ","We understand the importance of creating visually appealing outdoor spaces for shopping malls and apartments. Our landscaping services include the design and installation of attractive features such as entrance landscapes, courtyard gardens, seating areas, and decorative elements that enhance the overall ambiance and attract customers."));
        list1.add(new SubCategory("Landscaping of Private and Public Parks","Our expertise extends to designing and developing beautiful landscapes for private and public parks. We create functional and visually appealing spaces that incorporate elements like lawns, pathways, gardens, recreational areas, and water features, providing an inviting environment for people to relax and enjoy nature."));
        list1.add(new SubCategory("Landscaping of Hotel and Resorts ","We specialize in creating captivating landscapes that complement the architectural style and ambiance of hotels and resorts. Our team designs and installs outdoor spaces that incorporate elements such as lush gardens, serene water features, poolside landscapes, and outdoor seating areas to enhance the overall guest experience."));
        list1.add(new SubCategory("Maintaining Corporate Office Lawn","Our maintenance services include regular lawn care and upkeep for corporate office lawns. We provide services such as mowing, edging, fertilizing, weed control, and irrigation management to ensure the lawn remains healthy and well-maintained throughout the year."));
        list1.add(new SubCategory("Maintaining of Govt Office Lawn","We offer comprehensive lawn maintenance services for government office lawns. Our team ensures that the lawn receives regular care, including mowing, trimming, fertilization, pest control, and irrigation management, to keep it in optimal condition."));
        list1.add(new SubCategory("Maintaining of shopping mall","We provide regular maintenance services for shopping mall landscapes, including lawn care, plant bed maintenance, pruning, fertilization, and pest control. Our team ensures that the outdoor spaces of the shopping mall remain attractive and well-maintained for visitors."));
        list1.add(new SubCategory("Maintaining of Apartment Lawn and  other establishment lawn","We offer lawn maintenance services for apartments and other establishment lawns. Our services include mowing, trimming, weed control, fertilization, and overall lawn care to ensure a healthy and vibrant lawn that enhances the overall appearance of the property."));
        //list1.add(new SubCategory("Maintaining of Private and Public Parks",""));
        list1.add(new SubCategory("Maintaining of Golf Court","We specialize in golf course maintenance, including regular mowing, turf care, aeration, overseeding, pest control, and irrigation management. Our team understands the specific needs of golf courses and works diligently to maintain the optimal playing conditions"));
        list1.add(new SubCategory("Maintaining of Stadium ground","We provide maintenance services for stadium grounds, including turf care, line striping, mowing, weed control, fertilization, and overall turf management. We ensure that the stadium grounds are in top shape for various sporting events and activities."));
        list1.add(new SubCategory("Maintaining of Playground","We offer playground maintenance services, including the upkeep of the surrounding landscape, lawn care, mulching, weed control, and overall cleanliness. Our goal is to create a safe and visually appealing environment for children to enjoy."));
        list1.add(new SubCategory("Maintaining of Hotel and Resort Lawn","We provide comprehensive lawn maintenance services for hotels and resorts, ensuring that the outdoor areas remain well-groomed and visually appealing. Our services include lawn care, plant bed maintenance, tree and shrub care, fertilization, pest control, and irrigation management"));
       // list1.add(new SubCategory("Landscaping of any commercial space ",""));
        //list1.add(new SubCategory("Interior scaping",""));
        //  list1.add(new SubCategory("Turf management",""));

        detailList.put("Commercial Services", list1);
        return detailList;
    }
    private HashMap<String, List<SubCategory>> loadData6() {
        HashMap<String, List<SubCategory>> detailList = new HashMap<String, List<SubCategory>>();
        List<SubCategory> list1 = new ArrayList<SubCategory>();
        list1.add(new SubCategory("House and Villa  GARDENING (Ground and Roof Top )","Our experienced team specializes in designing and maintaining gardens for houses and villas. We work closely with clients to understand their preferences and create custom garden designs that complement their property. From soil preparation and planting to irrigation systems and ongoing maintenance, we ensure that the garden thrives and adds beauty to the surroundings."));
        list1.add(new SubCategory("House and Villa Flower plantation (Ground and Roof Top)","We offer flower plantation services for houses and villas, bringing vibrant colors and fragrant blooms to the landscape. Our team assists in selecting suitable flower varieties, preparing the soil, planting, and providing proper care to ensure healthy growth and blooming. We create visually appealing flower gardens that enhance the aesthetics of the property"));
        list1.add(new SubCategory("House and Villa  Fruit ,Vegetable and other farming","Our farming services cater to those interested in growing their own fruits, vegetables, or other crops. We provide guidance on suitable crop selection, soil preparation, planting techniques, and maintenance practices. Whether it's a small vegetable garden or a larger-scale farming project, we assist clients in achieving successful yields and enjoying the benefits of homegrown produce."));
        list1.add(new SubCategory("Apartment balcony Gardening (Grass and Flower)","We understand the importance of utilizing limited spaces in apartments effectively. Our balcony gardening services focus on transforming balconies into green havens. We create balcony gardens with lush grass and vibrant flowers, maximizing the available space while creating a serene and refreshing atmosphere."));
        list1.add(new SubCategory("Apartment balcony Fruit ,Vegetable farming","For those interested in apartment gardening, we offer fruit and vegetable farming services specifically designed for balcony spaces. We help clients select suitable crops, implement vertical gardening techniques, and provide guidance on container gardening and balcony-friendly farming practices. This allows apartment dwellers to enjoy the benefits of homegrown produce, even with limited space."));
        list1.add(new SubCategory("Interior scaping","Our interior scaping services revolve around creating green and serene environments within indoor spaces. We assist in selecting appropriate indoor plants, designing plant arrangements, and providing maintenance services to ensure optimal health and aesthetics. Indoor plants not only enhance the beauty of interior spaces but also contribute to improved air quality and a sense of well-being."));
        list1.add(new SubCategory("Any other gardening or farming service assistance","We offer comprehensive assistance for various gardening and farming needs. Whether it's landscape design, pest control, irrigation system installation, or any other gardening or farming-related service, our team is here to provide professional guidance and support."));

        detailList.put("Domestic Services", list1);
        return detailList;
    }
}