//sellandbuyrouter.js
const e = require("express");
const express = require("express");
const SellBuy = require("../mongoose/models/sellBuy");
const router = express.Router();

router.get("/", async (req, res) => {
  try {
    if (req.query.product) {
      const data = await SellBuy.find({ productName: req.query.product });
      res.status(200).json(data);
    } else if (req.query.sortBy) {
      let list = req.query.sortBy;
      let ord = -1;
      if (list.charAt(0) == "l") {
        ord = 1;
      }
      if (list.includes("S")) {
        const data = await SellBuy.find().sort({ soldPrice: ord });
        res.status(200).json(data);
      } else {
        const data = await SellBuy.find().sort({ costPrice: ord });
        res.status(200).json(data);
      }
    } else {
      const data = await SellBuy.find();
      res.status(200).json(data);
    }
  } catch (err) {
    res.status(200).json(data);
  }
});

// setting up the router
router.post("/", async (req, res, next) => {
  const data = new SellBuy(req.body);
  try {
    if (data.productName.length < 4) {
      const err = new Error(
        "product name should have minimum of four characters"
      );
      err.status = 400;
      next(err);
    } else if (data.costPrice <= 0) {
      const err = new Error( 
        "cost price value cannot be zero or negative value"
      );
      err.status = 400;
      next(err);
    } else if (data.soldPrice <= 0) {
      const err = new Error(
        "sold price value cannot be zero or negative value"
      );
      err.status = 400;
      next(err);
    } else {
      await data.save();
      res.status(201).json({ message: "Product Added" });
    }
  } catch (err) {
    res.status(200).json(data);
  }
});

router.patch("/:id", async (req, res, next) => {
  try {
    const data = req.body.soldPrice;
    if (data <= 0) {
      const err = new Error(
        "sold price value cannot be zero or negative value"
      );
      err.status = 400;
      next(err);
    } else {
      res.status(200).json({ message: "Updated Successfully" });
    }
  } catch (err) {
    res.status(200).json(data);
  }
});

// router.patch("/:id", async (req, res, next) => {
//     try {
//       const { id } = req.params;
//       const { soldPrice } = req.body;
  
//       if (!soldPrice || soldPrice <= 0) {
//         const err = new Error("Sold price value cannot be zero or negative");
//         err.status = 400;
//         throw err;
//       }
  
//       // Find the product by ID and update its sold price
//       const product = await SellBuy.findByIdAndUpdate(id, { soldPrice }, { new: true });
  
//       if (!product) {
//         const err = new Error("Product not found");
//         err.status = 404;
//         throw err;
//       }
  
//       res.status(200).json({ message: "Sold price updated successfully", product });
//     } catch (err) {
//       next(err);
//     }
//   });
  

router.delete("/:id", async (req, res) => {
  try {
    let id = req.params.id;
    if (id === undefined || id === "" || id === null) {
      res.status(400).json(data);
    }
    const data = await SellBuy.findByIdAndDelete(id);
    res.status(200).json({ message: "Deleted successfully" });
  } catch (err) {
    res.status(400).json({ error: err });
  }
});

const sellAndBuyRouter = new express.Router();

// code goes here for routes

// exporting the router

module.exports = router;

//app.js

const express = require("express");
const sellAndBuyRouter = require("./routers/sellAndBuy");
require("./mongoose/connect_db/mongoose");

const app = express();

app.use(express.json());
// app.use(bodyParser.json());

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "*");
  if (req.method === "OPTIONS") {
    res.header("Access-Control-Allow-Methods", "GET,DELETE,PATCH");
    return res.status(200).json({});
  }
  next();
});

app.use("/sellProduct", sellAndBuyRouter);
app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.json({ error: error.message });
});

module.exports = app;
