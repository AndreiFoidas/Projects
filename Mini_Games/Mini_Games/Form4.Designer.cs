namespace Mini_Games
{
    partial class Form4
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form4));
            this.panel1 = new System.Windows.Forms.Panel();
            this.button1 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.heart3 = new System.Windows.Forms.PictureBox();
            this.heart2 = new System.Windows.Forms.PictureBox();
            this.heart1 = new System.Windows.Forms.PictureBox();
            this.a2_drop = new System.Windows.Forms.PictureBox();
            this.a1_drop = new System.Windows.Forms.PictureBox();
            this.a3_drop = new System.Windows.Forms.PictureBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.timer3 = new System.Windows.Forms.Timer(this.components);
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.timer2 = new System.Windows.Forms.Timer(this.components);
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.heart3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.heart2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.heart1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.a2_drop)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.a1_drop)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.a3_drop)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.Transparent;
            this.panel1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panel1.Controls.Add(this.button1);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.heart3);
            this.panel1.Controls.Add(this.heart2);
            this.panel1.Controls.Add(this.heart1);
            this.panel1.Controls.Add(this.a2_drop);
            this.panel1.Controls.Add(this.a1_drop);
            this.panel1.Controls.Add(this.a3_drop);
            this.panel1.Controls.Add(this.pictureBox1);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(578, 307);
            this.panel1.TabIndex = 2;
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.Gold;
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(445, 237);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(121, 57);
            this.button1.TabIndex = 9;
            this.button1.Text = "RESTART";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Visible = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Gold;
            this.label1.Font = new System.Drawing.Font("Modern No. 20", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(512, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 21);
            this.label1.TabIndex = 8;
            this.label1.Text = "Scor: 0";
            // 
            // heart3
            // 
            this.heart3.BackColor = System.Drawing.Color.Transparent;
            this.heart3.Image = ((System.Drawing.Image)(resources.GetObject("heart3.Image")));
            this.heart3.Location = new System.Drawing.Point(88, 3);
            this.heart3.Name = "heart3";
            this.heart3.Size = new System.Drawing.Size(38, 40);
            this.heart3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.heart3.TabIndex = 7;
            this.heart3.TabStop = false;
            // 
            // heart2
            // 
            this.heart2.BackColor = System.Drawing.Color.Transparent;
            this.heart2.Image = ((System.Drawing.Image)(resources.GetObject("heart2.Image")));
            this.heart2.Location = new System.Drawing.Point(44, 3);
            this.heart2.Name = "heart2";
            this.heart2.Size = new System.Drawing.Size(38, 40);
            this.heart2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.heart2.TabIndex = 6;
            this.heart2.TabStop = false;
            // 
            // heart1
            // 
            this.heart1.BackColor = System.Drawing.Color.Transparent;
            this.heart1.Image = ((System.Drawing.Image)(resources.GetObject("heart1.Image")));
            this.heart1.Location = new System.Drawing.Point(0, 3);
            this.heart1.Name = "heart1";
            this.heart1.Size = new System.Drawing.Size(38, 40);
            this.heart1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.heart1.TabIndex = 5;
            this.heart1.TabStop = false;
            // 
            // a2_drop
            // 
            this.a2_drop.BackColor = System.Drawing.Color.Transparent;
            this.a2_drop.Image = ((System.Drawing.Image)(resources.GetObject("a2_drop.Image")));
            this.a2_drop.Location = new System.Drawing.Point(330, 0);
            this.a2_drop.Name = "a2_drop";
            this.a2_drop.Size = new System.Drawing.Size(32, 40);
            this.a2_drop.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.a2_drop.TabIndex = 3;
            this.a2_drop.TabStop = false;
            this.a2_drop.Visible = false;
            // 
            // a1_drop
            // 
            this.a1_drop.BackColor = System.Drawing.Color.Transparent;
            this.a1_drop.Image = ((System.Drawing.Image)(resources.GetObject("a1_drop.Image")));
            this.a1_drop.Location = new System.Drawing.Point(292, 0);
            this.a1_drop.Name = "a1_drop";
            this.a1_drop.Size = new System.Drawing.Size(32, 40);
            this.a1_drop.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.a1_drop.TabIndex = 2;
            this.a1_drop.TabStop = false;
            this.a1_drop.Visible = false;
            // 
            // a3_drop
            // 
            this.a3_drop.BackColor = System.Drawing.Color.Transparent;
            this.a3_drop.Image = ((System.Drawing.Image)(resources.GetObject("a3_drop.Image")));
            this.a3_drop.Location = new System.Drawing.Point(368, 0);
            this.a3_drop.Name = "a3_drop";
            this.a3_drop.Size = new System.Drawing.Size(32, 40);
            this.a3_drop.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.a3_drop.TabIndex = 1;
            this.a3_drop.TabStop = false;
            this.a3_drop.Visible = false;
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(236, 248);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(126, 47);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // timer3
            // 
            this.timer3.Interval = 30;
            this.timer3.Tick += new System.EventHandler(this.timer3_Tick);
            // 
            // timer1
            // 
            this.timer1.Enabled = true;
            this.timer1.Interval = 30;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // timer2
            // 
            this.timer2.Interval = 30;
            this.timer2.Tick += new System.EventHandler(this.timer2_Tick);
            // 
            // Form4
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.OliveDrab;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(578, 307);
            this.Controls.Add(this.panel1);
            this.Name = "Form4";
            this.Text = "Prinde Mere";
            this.Load += new System.EventHandler(this.Form4_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.heart3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.heart2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.heart1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.a2_drop)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.a1_drop)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.a3_drop)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.PictureBox heart3;
        private System.Windows.Forms.PictureBox heart2;
        private System.Windows.Forms.PictureBox heart1;
        private System.Windows.Forms.PictureBox a2_drop;
        private System.Windows.Forms.PictureBox a1_drop;
        private System.Windows.Forms.PictureBox a3_drop;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Timer timer3;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Timer timer2;
    }
}