// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MyMessage.proto

package com.lsk.lyphone;

public final class LYPhoneMessage {
  private LYPhoneMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LogonReqMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:LogonReqMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 acctID = 1;</code>
     */
    boolean hasAcctID();
    /**
     * <code>required int64 acctID = 1;</code>
     */
    long getAcctID();

    /**
     * <code>required string passwd = 2;</code>
     */
    boolean hasPasswd();
    /**
     * <code>required string passwd = 2;</code>
     */
    java.lang.String getPasswd();
    /**
     * <code>required string passwd = 2;</code>
     */
    com.google.protobuf.ByteString
        getPasswdBytes();
  }
  /**
   * Protobuf type {@code LogonReqMessage}
   */
  public static final class LogonReqMessage extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:LogonReqMessage)
      LogonReqMessageOrBuilder {
    // Use LogonReqMessage.newBuilder() to construct.
    private LogonReqMessage(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LogonReqMessage(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LogonReqMessage defaultInstance;
    public static LogonReqMessage getDefaultInstance() {
      return defaultInstance;
    }

    public LogonReqMessage getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LogonReqMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              acctID_ = input.readInt64();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              passwd_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.lsk.lyphone.LYPhoneMessage.internal_static_LogonReqMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lsk.lyphone.LYPhoneMessage.internal_static_LogonReqMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.class, com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.Builder.class);
    }

    public static com.google.protobuf.Parser<LogonReqMessage> PARSER =
        new com.google.protobuf.AbstractParser<LogonReqMessage>() {
      public LogonReqMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LogonReqMessage(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LogonReqMessage> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ACCTID_FIELD_NUMBER = 1;
    private long acctID_;
    /**
     * <code>required int64 acctID = 1;</code>
     */
    public boolean hasAcctID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 acctID = 1;</code>
     */
    public long getAcctID() {
      return acctID_;
    }

    public static final int PASSWD_FIELD_NUMBER = 2;
    private java.lang.Object passwd_;
    /**
     * <code>required string passwd = 2;</code>
     */
    public boolean hasPasswd() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string passwd = 2;</code>
     */
    public java.lang.String getPasswd() {
      java.lang.Object ref = passwd_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          passwd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string passwd = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPasswdBytes() {
      java.lang.Object ref = passwd_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        passwd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      acctID_ = 0L;
      passwd_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasAcctID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPasswd()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, acctID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getPasswdBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, acctID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getPasswdBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.lsk.lyphone.LYPhoneMessage.LogonReqMessage prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code LogonReqMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:LogonReqMessage)
        com.lsk.lyphone.LYPhoneMessage.LogonReqMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lsk.lyphone.LYPhoneMessage.internal_static_LogonReqMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lsk.lyphone.LYPhoneMessage.internal_static_LogonReqMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.class, com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.Builder.class);
      }

      // Construct using com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        acctID_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        passwd_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lsk.lyphone.LYPhoneMessage.internal_static_LogonReqMessage_descriptor;
      }

      public com.lsk.lyphone.LYPhoneMessage.LogonReqMessage getDefaultInstanceForType() {
        return com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.getDefaultInstance();
      }

      public com.lsk.lyphone.LYPhoneMessage.LogonReqMessage build() {
        com.lsk.lyphone.LYPhoneMessage.LogonReqMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lsk.lyphone.LYPhoneMessage.LogonReqMessage buildPartial() {
        com.lsk.lyphone.LYPhoneMessage.LogonReqMessage result = new com.lsk.lyphone.LYPhoneMessage.LogonReqMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.acctID_ = acctID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.passwd_ = passwd_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lsk.lyphone.LYPhoneMessage.LogonReqMessage) {
          return mergeFrom((com.lsk.lyphone.LYPhoneMessage.LogonReqMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lsk.lyphone.LYPhoneMessage.LogonReqMessage other) {
        if (other == com.lsk.lyphone.LYPhoneMessage.LogonReqMessage.getDefaultInstance()) return this;
        if (other.hasAcctID()) {
          setAcctID(other.getAcctID());
        }
        if (other.hasPasswd()) {
          bitField0_ |= 0x00000002;
          passwd_ = other.passwd_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAcctID()) {
          
          return false;
        }
        if (!hasPasswd()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lsk.lyphone.LYPhoneMessage.LogonReqMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lsk.lyphone.LYPhoneMessage.LogonReqMessage) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long acctID_ ;
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public boolean hasAcctID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public long getAcctID() {
        return acctID_;
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public Builder setAcctID(long value) {
        bitField0_ |= 0x00000001;
        acctID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public Builder clearAcctID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        acctID_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object passwd_ = "";
      /**
       * <code>required string passwd = 2;</code>
       */
      public boolean hasPasswd() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public java.lang.String getPasswd() {
        java.lang.Object ref = passwd_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            passwd_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public com.google.protobuf.ByteString
          getPasswdBytes() {
        java.lang.Object ref = passwd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          passwd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder setPasswd(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        passwd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder clearPasswd() {
        bitField0_ = (bitField0_ & ~0x00000002);
        passwd_ = getDefaultInstance().getPasswd();
        onChanged();
        return this;
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder setPasswdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        passwd_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:LogonReqMessage)
    }

    static {
      defaultInstance = new LogonReqMessage(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:LogonReqMessage)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LogonReqMessage_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_LogonReqMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017MyMessage.proto\"1\n\017LogonReqMessage\022\016\n\006" +
      "acctID\030\001 \002(\003\022\016\n\006passwd\030\002 \002(\tB!\n\017com.lsk." +
      "lyphoneB\016LYPhoneMessage"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_LogonReqMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LogonReqMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_LogonReqMessage_descriptor,
        new java.lang.String[] { "AcctID", "Passwd", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
