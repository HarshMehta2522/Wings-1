const e = require("express");
const express = require("express");
const SellBuy =require("../mongoose/models/sellBuy")

// setting up the router

const sellAndBuyRouter = new express.Router();

// code goes here for routes


// exporting the router

sellAndBuyRouter.delete("/sellProduct/:id",async(req,res)=>{
    try{
        let id = req.params.id;
        if(id=== undefined|| id ==="" || id===null)  
        res.status(400).json(data)
        const data= await SellBuy.findByIdAndDelete(id);
        res.status(200).json({message:"Deleted successfully"});
    }catch(err){
        res.status(400).json({error:err});
    }
})

sellAndBuyRouter.post("/sellProduct/",async(req,res)=>{
    const data = new SellBuy(req.body);
    try{
        if(data.productName.length<4){
            res.status(400).json({error:"product name should have minimum of four characters"})
        }else if(data.costPrice<=0){
            res.status(400).json({error:"cost price value cannot be zero or negative value"})

        }else {
            await data.save();
            res.status(201).json({message:"Product Added"})
        }
    }catch(err){
        res.status(400).json({error:err});
    }
})

sellAndBuyRouter.patch("/sellProduct/:id",async(req,res)=>{
    try{const id =req.params.id;
    const body= req.body ;
    const data= SellBuy.findById(id);
    if(body.soldPrice<=0)
        res.status(400).json({error:"sold price value cannot be zero or negative value"})
    da={
        soldPrice:body.soldPrice,
    }
    await SellBuy.findByIdAndUpdate(id,da);
    res.status(200).json({message:"Updated Successfully"});}
    catch(err){
        res.status(400);
    }    
})

sellAndBuyRouter.get("/sellProduct/",async(req,res)=>{
    try{
        if(req.query.product){
            const data= await SellBuy.find({productName:req.query.product});
            res.status(200).json(data);
        }
        else if(req.query.sortBy){
            if (req.query.sortBy==="lowerCostPrice"){
                const data = await SellBuy.find().sort({costPrice:1});
                res.status(200).json(data);

            }else if (req.query.sortBy==="higherCostPrice"){
                const data = await SellBuy.find().sort({costPrice:-1});
                res.status(200).json(data);

            }else if (req.query.sortBy==="lowerSoldPrice"){
                const data = await SellBuy.find().sort({soldPrice:1});
                res.status(200).json(data);

            }else if (req.query.sortBy==="higherSoldPrice"){
                const data = await SellBuy.find().sort({soldPrice:-1});
                res.status(200).json(data);

            }
            
        }else {
            const data = await SellBuy.find();
            res.status(200).json(data);

        }
    }catch(err){
        res.status(400);
    }    
})

module.exports = sellAndBuyRouter;
